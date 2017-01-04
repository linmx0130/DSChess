#include "clientadapter.h"
#include <vector>
int playerId;
/* Implement you AI here!
 * 
 * Functions you can use:
 * - Piece* getPieceAt(int x, int y);
 * - bool movePiece(int x, int y, int dx, int dy);
 * - std::vector<std::pair<int,int> > getPossibleStepsOfPiece(int x, int y);
 * Class to represent Piece:
 * Bishop, King, Queen, Knight, Rook, Pawn.
 *
 * If you find bugs, reported it and you will get bonus!
 **/
void doAction() {
	std::vector<std::pair<int, int>> mychess;
	for (int i = 0; i < 8; ++i) {
		for (int j = 0; j < 8; ++j) {
			Piece *p = getPieceAt(i, j);
			if (p != nullptr) {
				if (p->getOwner() == playerId) {
					mychess.push_back(std::make_pair(i, j));
				}
				delete p;
			}
		}
	}
	do {
		int t = rand() % mychess.size();
		std::vector<std::pair<int, int> > pl = getPossibleStepsOfPiece(mychess[t].first, mychess[t].second);
		if (pl.size() != 0) {
			int k = rand() % pl.size();
			movePiece(mychess[t].first, mychess[t].second, pl[k].first, pl[k].second);
			break;
		}
	} while (true);
}
Piece* pawnUpgrade(int x, int y) {
	return new Queen(playerId);
}