package myapp.Lab4TpChineseCheckers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class Client {
    Board board;
    Circle[][] circle;

    private Pane board_pane;
    private final GUIController controller;

    private double X, Y;
    private int noplayer;
    private int chpawnx = -1, chpawny = -1, destinx = -1, destiny = -1;
    private boolean pawnlock;

    List <Move> posmoves;

    @FXML
    private TextField textfield;

    ColorPicker[] array;

    private static int PORT = 8901;
    private Socket socket;
    private PrintWriter out;

    Task <Void> task = new Task <Void> () {
        @Override public Void call() throws IOException {
            BufferedReader in; 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response;
            try {
                response = in.readLine();
                if (response.startsWith("WELCOME")) {
                    setOnWelcome(response);
                }
                response = in.readLine();
                if (response.startsWith("GAMEMODE")) {
                    setGamemode(response);
                }

                while (true) {
                    if (in.ready() && (response=in.readLine()) != null) {
                        if (response.startsWith("VALIDMOVE")) {
                            setValidMoves(response);
                        } else if (response.startsWith("OTHER_MOVED")) {
                            setEachBoard(response);
                        } else if (response.startsWith("VICTORY")) {
                            sendWinMessage(response);
                            break;
                        } else if (response.startsWith("MESSAGE")) {
                            sendMessage(response);
                        }
                    }
                }

                quit();
            } catch (IOException e) {

            } finally {
                try {
                    socket.close();
                } catch (IOException e) {}
            }

            return null;
        }

        private void quit() {
            out.println("QUIT");
        }

        private void sendMessage(String response) {
            String message = response.substring(8);
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    controller.setText(message);
                    repaint();
                }
            });
        }

        private void sendWinMessage(String response) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    controller.setText("You've won!");
                }
            });

        }

        private void setEachBoard(String response) {
            int parsx, parsy, parsnx, parsny;
            parsx = Integer.parseInt(response.substring(12, 14));
            parsy = Integer.parseInt(response.substring(15, 17));
            parsnx = Integer.parseInt(response.substring(18, 20));
            parsny = Integer.parseInt(response.substring(21, 23));

            Platform.runLater(new Runnable() {
                @Override public void run() {
                    board.move(parsx, parsy, parsnx, parsny);
                    repaint();
                }
            });

        }

        private void setValidMoves(String response) {
            int parsx, parsy;
            parsx = Integer.parseInt(response.substring(10, 12));
            parsy = Integer.parseInt(response.substring(13, 15));

            Platform.runLater(new Runnable() {
                @Override public void run() {
                    posmoves.add(new Move(parsx, parsy));
                    repaint();
                    // adding new Move(parsx,parsy) into posmoves
                }
            });

        }

        private void setGamemode(String response) {
            String res = response.substring(9, 10);
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    switch (res) {
                        case "1":
                            {
                                create_game(1);
                            }
                            break;
                        case "2":
                            {
                                create_game(2);
                            }
                            break;
                        case "3":
                            {
                                create_game(3);
                            }
                            break;
                        case "4":
                            {
                                create_game(4);
                            }
                    }
                }
            });
        }

        private void setOnWelcome(String response) {
            int res = Integer.parseInt(response.substring(8, 9));
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    noplayer = res;
                    controller.setText("You are player " + noplayer);
                }
            });
        }
    };

    public Client(Pane board_pane, String serverAddress, GUIController controller) throws Exception {
        // Setup networking
        socket = new Socket(serverAddress, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);

        this.board_pane = board_pane;
        this.pawnlock = false;
        posmoves = new ArrayList < Move > ();

        this.controller = controller;
    }

    public void play() {
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }


    public void create_game(int gamemode) //checking conditions, if correct, create board 
    {
        setBoard(gamemode);
        if (board != null) {
            gameSettings();
            repaint();
            register_clicks(circle);
        }
    }

    private void setBoard(int gamemode) {
        switch (gamemode) {
            case 1:
                board = new Board121w2Players();
                break;
            case 2:
                board = new Board121w3Players();
                break;
            case 3:
                board = new Board121w4Players();
                break;
            case 4:
                board = new Board121w6Players();
                break;
            default:
                break;
        }

    }

    private void gameSettings() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Game settings (pawns colors)");
        array = new ColorPicker[board.getnoPlayers() + 2];
        Color[] def_colors = {
            null,
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.YELLOW,
            Color.ORANGE,
            Color.KHAKI
        };
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setPrefHeight(20 + (array.length) * 20);
        dialogPane.setPrefWidth(250);


        for (int a=0; a<array.length; a++) {
            Text text;
            switch (a) {
                case 0:
                    text = new Text("Not in game");
                    break;
                case 1:
                    text = new Text("In game");
                    break;
                default:
                    text = new Text("Player " + (a - 1));
                    break;
            }
            text.setLayoutX(5);
            text.setLayoutY(20 + a*20);
            array[a] = new ColorPicker();
            array[a].setLayoutX(100);
            array[a].setLayoutY(16 + a*20);
            array[a].setValue(def_colors[a]);
            dialogPane.getChildren().addAll(text, array[a]);
        }

        ButtonType start = new ButtonType("Start");
        dialog.getDialogPane().getButtonTypes().add(start);
        Button okButton = (Button) dialog.getDialogPane().lookupButton(start);
        dialog.show();
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());

        circle = new Circle[21][21];
        this.X = board_pane.getWidth();
        this.Y = board_pane.getHeight();

        okButton.setOnAction((ActionEvent event) -> {
            repaint();
        });

    }

    public void register_clicks(Circle[][] circle) {
        board_pane.setOnMousePressed((MouseEvent event2) -> {
            double x = event2.getX();
            double y = event2.getY();
            for (int a = 0; a < circle.length; a++) {
                for (int b = 0; b < circle[a].length; b++) {
                    if (circle[a][b].contains(x, y)) {
                        if (this.board.getSquare(a, b) == this.noplayer && pawnlock == false) {
                            chpawnx=a;
                            chpawny=b;
                            String sx, sy;
                            if (a < 10)
                                sx= "0" + Integer.toString(a);
                            else
                                sx=Integer.toString(a);
                            if (b < 10)
                                sy="0"+Integer.toString(b);
                            else
                                sy=Integer.toString(b);
                            out.println("PAWN " + sx + " " + sy);
                            removeselect();
                            //send command to server PAWN a b
                        } else
                        if (this.board.getSquare(a, b) == 0) {
                            if (chpawnx != -1 && chpawny != -1) {
                                destinx = a;
                                destiny = b;
                            }
                        }
                        if (chpawnx != -1 && chpawny != -1 && destinx != -1 && destiny != -1) {
                            move(chpawnx, chpawny, destinx, destiny);
                        }
                    }
                }
            }

        });
    }

    public void set_colors(Circle circle, int player, ColorPicker[] array) //color settings
    {
        for (int a = 0; a < array.length; a++) {
            if (player == a - 1) {
                circle.setFill(array[a].getValue());
            }
        }
    }
    public void repaint() {
        for (int a = 0; a < 21; a++) {
            for (int b = 0; b < 21; b++) {
                circle[a][b] = new Circle();
                circle[a][b].setRadius(X / 54);
                circle[a][b].setCenterX((X / 54) * 20 + (X / 27) * a - (X / 54) * b);
                circle[a][b].setCenterY(70 + (Y / 27) * b);
                set_colors(circle[a][b], board.p[a][b], array);
                for (Move m: posmoves) {
                    if (m.x == a && m.y == b) {
                        circle[a][b].setFill(Color.GRAY);
                    }
                }
                circle[a][b].setVisible(true);
                board_pane.getChildren().add(circle[a][b]);
            }
        }
    }
    public void move(int x, int y, int nx, int ny) {
        for (Move move: posmoves) {
            if (move.x == nx && move.y == ny) {
                board.move(x, y, nx, ny);
                chpawnx = nx;
                chpawny = ny;
                repaint();
                destinx = destiny = -1;
                pawnlock = true;

                String sx, sy;

                if (nx < 10)
                    sx = "0" + Integer.toString(nx);
                else
                    sx = Integer.toString(nx);
                if (ny < 10)
                    sy = "0" + Integer.toString(ny);
                else
                    sy = Integer.toString(ny);
                removeselect();
                out.println("MOVE " + sx + " " + sy);
                // send to server MOVE sx sy
                break;
            }
        }
    }
    public void endmove() {
        chpawnx = chpawny = -1;
        destinx = destiny = -1;
        pawnlock = false;

        out.println("END");
        removeselect();
    }

    public void selpossmov(List <Move> m) {
        this.posmoves = new ArrayList<>(m);
    }
    public void removeselect() {
        this.posmoves.clear();
        repaint();
    }

}
