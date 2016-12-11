#include "net.h"
#include "clientadapter.h"
#include <sstream>
#include <vector>
#include <utility>

Piece* buildPieceFromString(std::string desc) {
	std::istringstream sin(desc);
	std::string type;
	sin >> type;
	int owner;
	sin >> owner;
	Piece* ret = nullptr;
	if (type=="Bishop") {
		ret = new Bishop(owner);
	}
	if (type=="King") {
		ret = new King(owner);
	}
    if (type == "Knight") {
		ret = new Knight(owner);
	}
	if (type == "Queen") {
		ret = new Queen(owner);
	}
	if (type=="Rook") {
		ret = new Rook(owner);
	}
	if (type == "Pawn") {
		int direction;
		sin >> direction;
		bool isPoorPawn;
		sin >> isPoorPawn;
		ret = new Pawn(owner, direction);
		((Pawn*)ret)->setPoorPawn(isPoorPawn);
	 }
	 return ret;
}

Piece* getPieceAt(int x, int y) {
	char buf[256];
	memset(buf, 0, sizeof(buf));
	sprintf(buf, "P %d %d\n", x, y);
	sendData(buf);
	std::string response = receiveData();
	return buildPieceFromString(response);
}

bool movePiece(int x, int y, int dx, int dy) {
	char buf[256];
	memset(buf, 0, sizeof(buf));
	sprintf(buf, "M %d %d %d %d\n", x, y,dx,dy);
	sendData(buf);
	std::string response = receiveData();
	if (response == "true\n") return true;
	return false;
}

std::vector<std::pair<int, int> > getPossibleStepsOfPiece(int x, int y) {
	char buf[256];
	memset(buf, 0, sizeof(buf));
	sprintf(buf, "S %d %d\n", x, y);
	sendData(buf);
	std::string response = receiveData();
	std::vector<std::pair<int,int> > ret;
	std::istringstream sin(response);
	do {
		int x, y;
		std::string buf2;
		if (!(sin >> buf2)) {
			break;
		}
		sin >> x;
		sin >> buf2;
		sin >> y;
		sin >> buf2; 
		ret.push_back(std::make_pair(x, y));
	} while (true);
	return ret;
}