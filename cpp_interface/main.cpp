#include <iostream>
#include <sstream>
#include <string>
#include <cstdlib>
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>
#include <stdio.h>
#include "clientadapter.h"
#include "net.h"

void doAction();
Piece* pawnUpgrade(int x, int y);

#pragma comment(lib, "Ws2_32.lib")
WSADATA wsaData;
const unsigned int DEFAULT_BUFLEN = 2048;
char recvbuf[DEFAULT_BUFLEN];
SOCKET ConnectSocket = INVALID_SOCKET;

void sendData(const char* sendbuf) {
	int iResult = send(ConnectSocket, sendbuf, (int)strlen(sendbuf), 0);
	if (iResult == SOCKET_ERROR) {
		printf("send failed: %d\n", WSAGetLastError());
		closesocket(ConnectSocket);
		WSACleanup();
		exit(1);
	}
}

std::string receiveData() {
	std::string ret;
	do {
		ZeroMemory(recvbuf, DEFAULT_BUFLEN);
		int recvbuflen = DEFAULT_BUFLEN;
		int iResult = recv(ConnectSocket, recvbuf, recvbuflen-1, 0);
		if (iResult > 0) {
			ret += recvbuf;
		}
		if (strlen(recvbuf) != recvbuflen - 1) {
			break;
		}
	} while (true);
	return ret;
}

int main() {
	
	int iResult;
	// Initialize Winsock
	iResult = WSAStartup(MAKEWORD(2, 2), &wsaData);
	if (iResult != 0) {
		printf("WSAStartup failed: %d\n", iResult);
		return 1;
	}
	struct addrinfo *result = NULL, *ptr = NULL, hints;

	ZeroMemory(&hints, sizeof(hints));
	hints.ai_family = AF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;
	// Resolve the server address and port
	iResult = getaddrinfo("localhost", "1471", &hints, &result);
	if (iResult != 0) {
		printf("getaddrinfo failed: %d\n", iResult);
		WSACleanup();
		return 1;
	}
	
	// Attempt to connect to the first address returned by the call to getaddrinfo
	ptr = result;
	// Create a SOCKET for connecting to server
	ConnectSocket = socket(ptr->ai_family, ptr->ai_socktype, ptr->ai_protocol);
	if (ConnectSocket == INVALID_SOCKET) {
		printf("Error at socket(): %ld\n", WSAGetLastError());
		freeaddrinfo(result);
		WSACleanup();
		return 1;
	}
	// Connect to server.
	iResult = connect(ConnectSocket, ptr->ai_addr, (int)ptr->ai_addrlen);
	if (iResult == SOCKET_ERROR) {
		closesocket(ConnectSocket);
		ConnectSocket = INVALID_SOCKET;
	}

	// Should really try the next address returned by getaddrinfo
	// if the connect call failed
	// But for this simple example we just free the resources
	// returned by getaddrinfo and print an error message
	
	freeaddrinfo(result);

	if (ConnectSocket == INVALID_SOCKET) {
		printf("Unable to connect to server!\n");
		WSACleanup();
		return 1;
	}

	// ask for key
	std::cout << "Key: ";
	std::string key;
	std::cin >> key;

	
	int recvbuflen = DEFAULT_BUFLEN;
	std::string sendbufs("KEY");
	sendbufs += key;
	sendbufs += "\n";
	
	
	sendData(sendbufs.c_str());

	// Receive data until the server closes the connection
	do {
		std::string d = receiveData();
		std::istringstream sin(d);
		std::string command;
		sin >> command;
		if (command == "ACTION!") {
			doAction();
		}
		if (command== "PAWNUPGRADE!") {
			int x, y;
			sin >> x >> y;
			Piece* ret = pawnUpgrade(x, y);
			std::string databuf = ret->toString() + "\n";
			sendData(databuf.c_str());
		}
	} while (true);

	iResult = shutdown(ConnectSocket, SD_SEND);
	if (iResult == SOCKET_ERROR) {
		printf("shutdown failed: %d\n", WSAGetLastError());
		closesocket(ConnectSocket);
		WSACleanup();
		return 1;
	}
	return 0;
}