#pragma once
#include <string>
#include <stdio.h>
#include <vector>
#include <utility>
class Piece {
protected:
	int owner;
public:
	Piece(int owner) :owner(owner) {};
	int getOwner() {
		return this->owner;
	}
	virtual std::string toString() = 0;
};
class Bishop:public Piece{
public:
	Bishop(int owner) :Piece(owner) {};
	std::string toString() override {
		std::string ret("Bishop ");
		ret += (this->owner + '0');
		return ret;
	}
};
class King :public Piece {
public:
	King(int owner) :Piece(owner) {};
	std::string toString() override {
		std::string ret("King ");
		ret += (this->owner + '0');
		return ret;
	}
};
class Knight : public Piece {
public:
	Knight(int owner) :Piece(owner) {};
	std::string toString() override {
		std::string ret("Knight ");
		ret += (this->owner + '0');
		return ret;
	}
};
class Queen : public Piece {
public:
	Queen(int owner) :Piece(owner) {};
	std::string toString() override {
		std::string ret("Queen ");
		ret += (this->owner + '0');
		return ret;
	}
};
class Rook : public Piece {
public:
	Rook(int owner) :Piece(owner) {};
	std::string toString() override {
		std::string ret("Rook ");
		ret += (this->owner + '0');
		return ret;
	}
};
class Pawn : public Piece {
private:
	int direction;
	bool _isPoorPawn;
public:
	Pawn(int owner, int direction) :Piece(owner),direction(direction) {};
	void setPoorPawn(bool isPoorPawn) {
		this->_isPoorPawn = isPoorPawn;
	}
	bool isPoorPawn() {
		return this->_isPoorPawn;
	}
	int getDirection() { return this->direction; }
	std::string toString() override {
		char buf[100];
		memset(buf, 0, sizeof(buf));
		sprintf(buf, "Pawn %d %d ", this->owner, this->direction);
		std::string ret(buf);
		if (this->isPoorPawn()) {
			ret += "true";
		}
		else {
			ret += "false";
		}
		return ret;
	}
};

Piece* buildPieceFromString(std::string desc);

Piece* getPieceAt(int x, int y);
bool movePiece(int x, int y, int dx, int dy);
std::vector<std::pair<int,int> > getPossibleStepsOfPiece(int x, int y);
