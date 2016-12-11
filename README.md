
DSChess
=====
The platform for DS Project 2.

## AI API
All ai for this game should implement *com.sweetdum.dschess.ai.ChessAI*.
All methods of this interface would receive an instance of *com.sweetdum.dschess.game.GameControllerAdapter*, which 
could be used to access the chess board.

Refer to the Javadoc of *GameControllerAdapter* for more details.

##YourAI
In order to support C++, this platform utilized Client/Server framework. 

* For Java user, you can simply modify *com.sweetdum.dschess.client.YourAI*.
* For C++ user, you can simply modify *yourAI.cpp*.

All networking routine are implemented, so just play with APIs.

