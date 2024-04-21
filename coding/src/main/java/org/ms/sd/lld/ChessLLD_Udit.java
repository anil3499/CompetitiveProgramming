package org.ms.sd.lld;/*
package ms.sd.lld;

*/
/*
Problem Statements: Chess LLD
We have to design simple chess game. Chess is actually a very popular game. You can read more about it online on wikipedia or on some other places.

Entities
If you have already played it already then you might already know this. But to ensure that we all are on the same page, I am defining some here:

Board: Board is the one entity represents an actual board on which which you play this game.
Cell: A board consists of a grid of cells.
Player: Someone who is actually playing right.
Piece: There are various types of pieces as explained below.
Pieces and their moves:
King: Key entity in chess. If your king is killed then you lose. Its also called checkmate.
Queen: It can move any number of steps in a single move and in any direction.
Rook: It only moves in horizontal and vertical direction but can move any number of steps in single move.
Bishop: It only moves in diagonal direction but can move any number of steps in single move.
Knight: It makes L shaped moves. Check online for more details about it.
Pawn: It can move 1 step forward vertically. If it is its first turn, then it can also choose to make 2 steps in single move.
Note: All pieces except Knight cannot jump any other piece. Knight can jump over other pieces.
Expectations
Code should be functionally correct.
Code should be modular and readable. Clean and professional level code.
Code should be extesible and scalable. Means it should be able to accomodate new requirements with minimal changes.
Code should have good OOPs design.
 *//*


//models=========================================================

import com.google.common.collect.ImmutableList;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static ms.sd.lld.ListHelpers.removeDuplicates;

@Getter
class Board {
    int boardSize;
    Cell[][] cells;

    public Board(int boardSize, Cell[][] cells) {
        this.boardSize = boardSize;
        this.cells = cells;
    }

    */
/**
     * Helper method to return cell to the left of current cell.
     *//*

    public Cell getLeftCell(Cell cell) {
        return getCellAtLocation(cell.getX(), cell.getY() - 1);
    }

    */
/**
     * Helper method to return cell to the right of current cell.
     *//*

    public Cell getRightCell(Cell cell) {
        return getCellAtLocation(cell.getX(), cell.getY() + 1);
    }

    */
/**
     * Helper method to return cell to the up of current cell.
     *//*

    public Cell getUpCell(Cell cell) {
        return getCellAtLocation(cell.getX() + 1, cell.getY());
    }

    */
/**
     * Helper method to return cell to the down of current cell.
     *//*

    public Cell getDownCell(Cell cell) {
        return getCellAtLocation(cell.getX() - 1, cell.getY());
    }

    */
/**
     * Helper method to return cell at a given location.
     *//*

    public Cell getCellAtLocation(int x, int y) {
        if (x >= boardSize || x < 0) {
            return null;
        }
        if (y >= boardSize || y < 0) {
            return null;
        }

        return cells[x][y];
    }

    */
/**
     * Helper method to determine whether the player is on check on the current board.
     *//*

    public boolean isPlayerOnCheck(Player player) {
        return checkIfPieceCanBeKilled(player.getPiece(PieceType.KING), PieceCellOccupyBlockerFactory.kingCheckEvaluationBlockers(), player);
    }


    public boolean checkIfPieceCanBeKilled(Piece targetPiece, List<PieceCellOccupyBlocker> cellOccupyBlockers, Player player) {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                Piece currentPiece = getCellAtLocation(i, j).getCurrentPiece();
                if (currentPiece != null && !currentPiece.isPieceFromSamePlayer(targetPiece)) {
                    List<Cell> nextPossibleCells = currentPiece.nextPossibleCells(this, cellOccupyBlockers, player);
                    if (nextPossibleCells.contains(targetPiece.getCurrentCell())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


@Getter
class Cell {

    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Setter
    private Piece currentPiece;

    public boolean isFree() {
        return currentPiece == null;
    }
}

enum Color {
    BLACK,
    WHITE
}

enum PieceType {
    KING,
    QUEEN,
    ROOK,
    KNIGHT,
    BISHOP,
    PAWN
}

@Getter
abstract class Player {
    List<Piece> pieces;

    public Player(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece getPiece(PieceType pieceType) {
        for (Piece piece : getPieces()) {
            if (piece.getPieceType() == pieceType) {
                return piece;
            }
        }
        throw new PieceNotFoundException();
    }

    abstract public PlayerMove makeMove();
}

@Getter
class Piece {
    private boolean isKilled = false;
    private final Color color;
    private final List<PossibleMovesProvider> movesProviders;
    private Integer numMoves = 0;
    PieceType pieceType;

    @Setter
    @NonNull
    private Cell currentCell;

    public Piece(@NonNull final Color color, @NonNull final List<PossibleMovesProvider> movesProviders, @NonNull final PieceType pieceType) {
        this.color = color;
        this.movesProviders = movesProviders;
        this.pieceType = pieceType;
    }

    public void killIt() {
        this.isKilled = true;
    }

    public void move(Player player, Cell toCell, Board board, List<PieceCellOccupyBlocker> additionalBlockers) {
        if (isKilled) {
            throw new InvalidMoveException();
        }
        List<Cell> nextPossibleCells = nextPossibleCells(board, additionalBlockers, player);
        if (!nextPossibleCells.contains(toCell)) {
            throw new InvalidMoveException();
        }

        killPieceInCell(toCell);
        this.currentCell.setCurrentPiece(null);
        this.currentCell = toCell;
        this.currentCell.setCurrentPiece(this);
        this.numMoves++;
    }


    private void killPieceInCell(Cell targetCell) {
        if (targetCell.getCurrentPiece() != null) {
            targetCell.getCurrentPiece().killIt();
        }
    }


    public List<Cell> nextPossibleCells(Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        for (PossibleMovesProvider movesProvider : this.movesProviders) {
            List<Cell> cells = movesProvider.possibleMoves(this, board, additionalBlockers, player);
            if (cells != null) {
                result.addAll(cells);
            }
        }
        return removeDuplicates(result);
    }


    public boolean isPieceFromSamePlayer(Piece piece) {
        return piece.getColor().equals(this.color);
    }
}



//moves================================================================
interface NextCellProvider {
    Cell nextCell(Cell cell);
}

abstract class PossibleMovesProvider {
    int maxSteps;
    MoveBaseCondition baseCondition;
    PieceMoveFurtherCondition moveFurtherCondition;
    PieceCellOccupyBlocker baseBlocker;

    public PossibleMovesProvider(int maxSteps, MoveBaseCondition baseCondition, PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        this.maxSteps = maxSteps;
        this.baseCondition = baseCondition;
        this.moveFurtherCondition = moveFurtherCondition;
        this.baseBlocker = baseBlocker;
    }

    */
/**
     * Public method which actually gives all possible cells which can be reached via current type of move.
     *//*

    public List<Cell> possibleMoves(Piece piece, Board inBoard, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        if (baseCondition.isBaseConditionFullfilled(piece)) {
            return possibleMovesAsPerCurrentType(piece, inBoard, additionalBlockers, player);
        }
        return null;
    }

    */
/**
     * Abstract method which needs to be implemented by each type of move to give possible moves as per their behaviour.
     *//*

    protected abstract List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player);

    */
/**
     * Helper method used by all the sub types to create the list of cells which can be reached.
     *//*

    protected List<Cell> findAllNextMoves(Piece piece, NextCellProvider nextCellProvider, Board board, List<PieceCellOccupyBlocker> cellOccupyBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        Cell nextCell = nextCellProvider.nextCell(piece.getCurrentCell());
        int numSteps = 1;
        while (nextCell != null && numSteps <= maxSteps) {
            if (checkIfCellCanBeOccupied(piece, nextCell, board, cellOccupyBlockers, player)) {
                result.add(nextCell);
            }
            if (!moveFurtherCondition.canPieceMoveFurtherFromCell(piece, nextCell, board)) {
                break;
            }

            nextCell = nextCellProvider.nextCell(nextCell);
            numSteps++;
        }
        return result;
    }


    private boolean checkIfCellCanBeOccupied(Piece piece, Cell cell, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        if (baseBlocker != null && baseBlocker.isCellNonOccupiableForPiece(cell, piece, board, player)) {
            return false;
        }
        for (PieceCellOccupyBlocker cellOccupyBlocker : additionalBlockers) {
            if (cellOccupyBlocker.isCellNonOccupiableForPiece(cell, piece, board, player)) {
                return false;
            }
        }
        return true;
    }
}

class PossibleMovesProviderDiagonal extends PossibleMovesProvider {


    public PossibleMovesProviderDiagonal(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        return null;
    }
}


class PossibleMovesProviderHorizontal extends PossibleMovesProvider {

    public PossibleMovesProviderHorizontal(int maxSteps, MoveBaseCondition baseCondition,
                                           PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, final Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        result.addAll(findAllNextMoves(piece, board::getLeftCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightCell, board, additionalBlockers, player));
        return result;
    }
}

class PossibleMovesProviderVertical extends PossibleMovesProvider {
    private VerticalMoveDirection verticalMoveDirection;

    public PossibleMovesProviderVertical(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker,
                                         VerticalMoveDirection verticalMoveDirection) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
        this.verticalMoveDirection = verticalMoveDirection;
    }


    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        if (this.verticalMoveDirection == VerticalMoveDirection.UP || this.verticalMoveDirection == VerticalMoveDirection.BOTH) {
            result.addAll(findAllNextMoves(piece, board::getUpCell, board, additionalBlockers, player));
        }
        if (this.verticalMoveDirection == VerticalMoveDirection.DOWN || this.verticalMoveDirection == VerticalMoveDirection.BOTH) {
            result.addAll(findAllNextMoves(piece, board::getDownCell, board, additionalBlockers, player));
        }
        return result;
    }
}

enum VerticalMoveDirection {
    UP,
    DOWN,
    BOTH
}

//helpers=============================================================
class ListHelpers {

    public static <T> List<T> removeDuplicates(List<T> list) {
        List<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
}


//conditions==================================================
interface MoveBaseCondition {
    boolean isBaseConditionFullfilled(Piece piece);
}
class MoveBaseConditionFirstMove implements MoveBaseCondition {

    public boolean isBaseConditionFullfilled(Piece piece) {
        return piece.getNumMoves() == 0;
    }
}
class NoMoveBaseCondition implements MoveBaseCondition {
    public boolean isBaseConditionFullfilled(Piece piece) {
        return true;
    }
}
interface PieceCellOccupyBlocker {

    boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player);
}
class PieceCellOccupyBlockerFactory {

    public static PieceCellOccupyBlocker defaultBaseBlocker() {
        return new PieceCellOccupyBlockerSelfPiece();
    }

    public static List<PieceCellOccupyBlocker> defaultAdditionalBlockers() {
        return ImmutableList.of(new PieceCellOccupyBlockerKingCheck());
    }

    public static List<PieceCellOccupyBlocker> kingCheckEvaluationBlockers() {
        return ImmutableList.of();
    }
}
class PieceCellOccupyBlockerKingCheck implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(final Cell cell, final Piece piece, final Board board, Player player) {
        Cell pieceOriginalCell = piece.getCurrentCell();
        piece.setCurrentCell(cell);
        boolean playerGettingCheckByMove = board.isPlayerOnCheck(player);
        piece.setCurrentCell(pieceOriginalCell);
        return playerGettingCheckByMove;
    }
}
class PieceCellOccupyBlockerSelfPiece implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player) {
        if (cell.isFree()) {
            return false;
        }
        return cell.getCurrentPiece().getColor() == piece.getColor();
    }
}
interface PieceMoveFurtherCondition {

    boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board);
}

class PieceMoveFurtherConditionDefault implements PieceMoveFurtherCondition {

    @Override
    public boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board) {
        return cell.isFree();
    }
}



//exception==================================================
class InvalidMoveException extends RuntimeException {
}
class PieceNotFoundException extends RuntimeException {
}

//play game=================================================
@Getter
class PlayerMove {

    Piece piece;
    Cell toCell;
}

class GameController {

    public static void gameplay(List<Player> players, Board board) {
        int currentPlayer = 0;
        while (true) {
            Player player = players.get(currentPlayer);
            //TODO: Check if current player has any move possible. If no move possible, then its checkmate.
            PlayerMove playerMove = player.makeMove();
            playerMove.getPiece().move(player, playerMove.getToCell(), board, PieceCellOccupyBlockerFactory.defaultAdditionalBlockers());
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }
}



public class ChessLLD_Udit {
}
*/
