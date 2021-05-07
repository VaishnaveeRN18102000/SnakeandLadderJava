package sample;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application
{
    public int ra;
    public Label rares, gares;
    public static final int T_size = 80, wid = 10, hei = 10;
    public Circle pl1, pl2;
    public int pl1pos = 1, pl2pos = 1, pl1xpos = 150, pl1ypos = 840, pl2xpos = 640, pl2ypos = 840, posCir1 = 1, posCir2 = 1;
    public boolean pl1t = true, pl2t = true, gaSt = false;
    public Button gamBut, bu1, bu2;
    private final Group til_grp = new Group();
    private Parent createContent()
    {
        Pane root = new Pane();
        root.setPrefSize(wid * T_size, (hei * T_size) + 80);
        root.getChildren().addAll(til_grp);
        for(int i = 0; i < wid; i++)
        {
            for(int j = 0; j < hei; j++)
            {
                Tile tile = new Tile(T_size, T_size);
                tile.setTranslateX(j * T_size);
                tile.setTranslateY(i * T_size);
                til_grp.getChildren().add(tile);
            }
        }
        pl1 = new Circle(40);
        pl1.setId("Player 1");
        pl1.setFill(Color.GREEN);
        pl1.getStyleClass().add("sample/style.css");
        pl1.setTranslateX(pl1xpos);
        pl1.setTranslateY(pl1ypos);
        pl2 = new Circle(40);
        pl2.setId("Player 2");
        pl2.setFill(Color.RED);
        pl2.getStyleClass().add("sample/style.css");
        pl2.setTranslateX(pl2xpos);
        pl2.setTranslateY(pl2ypos);
        bu1 = new Button("Player 1");
        bu1.setTranslateX(20);
        bu1.setTranslateY(820);
        bu1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                if(gaSt)
                {
                    if(pl1t)
                    {
                        getDiceValue();
                        rares.setText(String.valueOf(ra));
                        pl1pos += ra;
                        movePlayer1();
                        translatePlayer(pl1xpos, pl1ypos, pl1);
                        pl1t = false;
                        pl2t = true;
                        if(gaSt)
                        {
                            gares.setText("Player 2 turn");
                        }
                    }
                }
            }
        });
        bu2 = new Button("Player 2");
        bu2.setTranslateX(700);
        bu2.setTranslateY(820);
        bu2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                if(gaSt)
                {
                    if(pl2t)
                    {
                        getDiceValue();
                        rares.setText(String.valueOf(ra));
                        pl2pos += ra;
                        movePlayer2();
                        translatePlayer(pl2xpos, pl2ypos, pl2);
                        pl2t = false;
                        pl1t = true;
                        if(gaSt)
                        {
                            gares.setText("Player 1 turn");
                        }
                    }
                }
            }
        });
        gamBut = new Button("Start Game");
        gamBut.setTranslateX(350);
        gamBut.setTranslateY(820);
        gamBut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                if(!gaSt)
                {
                    gaSt = true;
                    rares.setText("Dice Result");
                    rares.setTranslateX(220);
                    gamBut.setText("Game Started");
                    pl1xpos = 40;
                    pl1ypos = 760;
                    pl2xpos = 40;
                    pl2ypos = 760;
                    pl1pos = 1;
                    pl2pos = 1;
                    posCir1 = 1;
                    posCir2 = 1;
                    pl1.setTranslateX(pl1xpos);
                    pl1.setTranslateY(pl1ypos);
                    pl2.setTranslateX(pl2xpos);
                    pl2.setTranslateY(pl2ypos);
                    ra = (int)(Math.random() * 2 +1);
                    if(ra == 1)
                    {
                        pl1t = true;
                        gares.setText("Player 1 Turn");
                    }
                    else
                    {
                        pl2t = true;
                        gares.setText("Player 2 Turn");
                    }
                }
            }
        });
        rares = new Label("Dice Result");
        rares.setTranslateX(230);
        rares.setTranslateY(830);
        gares = new Label("Game Result");
        gares.setTranslateX(490);
        gares.setTranslateY(830);
        Image img = new Image("snakeladder.jpg");
        ImageView bgimg = new ImageView();
        bgimg.setImage(img);
        bgimg.setFitWidth(800);
        bgimg.setFitHeight(800);
        til_grp.getChildren().addAll(bgimg, pl1, pl2, bu1, bu2, gamBut, rares, gares);
        return root;
    }
    public void getDiceValue()
    {
        ra = (int)(Math.random() * 6 +1);
    }
    public void movePlayer1()
    {
        if (pl1xpos == 40 && pl1ypos == 40)
        {
            pl1xpos = 40;
            pl1ypos = 40;
        }
        for (int i = 0; i < ra; i++)
        {
            if (posCir1 % 2 == 1)
            {
                pl1xpos += 80;
            }
            if (posCir1 % 2 == 0)
            {
                pl1xpos -= 80;
            }
            if (pl1xpos > 760)
            {
                pl1ypos -= 80;
                pl1xpos -= 80;
                posCir1++;
            }
            if (pl1xpos < 40)
            {
                pl1ypos -= 80;
                pl1xpos += 80;
                posCir1++;
            }
            if (pl1xpos < 30 || pl1ypos < 30)
            {
                pl1xpos = 40;
                pl1ypos = 40;
                gares.setTranslateX(530);
                gares.setText("Player 1 Won");
                gamBut.setText("Start Again");
                gaSt = false;
            }
        }
        moveNewPlayer1();
    }
    // New positions of player1 for Snakes and Ladders
    public void moveNewPlayer1()
    {
        if(pl1pos == 3)
        {
            pl1xpos = 120; pl1ypos = 520;
            posCir1 += 3;
            pl1pos = 39;
        }
        if(pl1pos == 10)
        {
            pl1xpos = 680; pl1ypos = 680;
            posCir1 += 1;
            pl1pos = 12;
        }
        if(pl1pos == 27)
        {
            pl1xpos = 600; pl1ypos = 360;
            posCir1 += 3;
            pl1pos = 53;
        }
        if(pl1pos == 56)
        {
            pl1xpos = 280; pl1ypos = 120;
            posCir1 += 3;
            pl1pos = 84;
        }
        if(pl1pos == 61 || pl1pos == 99)
        {
            pl1xpos = 40; pl1ypos = 40;
            posCir1 += 3;
            pl1pos = 99;
        }
        if(pl1pos == 72)
        {
            pl1xpos = 760; pl1ypos = 120;
            posCir1 += 1;
            pl1pos = 90;
        }
        if(pl1pos == 16)
        {
            pl1xpos = 600; pl1ypos = 680;
            pl1pos = 13;
        }
        if(pl1pos == 31)
        {
            pl1xpos = 280; pl1ypos = 760;
            posCir1 -= 3;
            pl1pos = 4;
        }
        if(pl1pos == 47)
        {
            pl1xpos = 360; pl1ypos = 600;
            posCir1 -= 2;
            pl1pos = 25;
        }
        if(pl1pos == 63)
        {
            pl1xpos = 40; pl1ypos = 360;
            posCir1 -= 1;
            pl1pos = 60;
        }
        if(pl1pos == 66)
        {
            pl1xpos = 680; pl1ypos = 360;
            posCir1 -= 1;
            pl1pos = 52;
        }
        if(pl1pos == 97)
        {
            pl1xpos = 440; pl1ypos = 200;
            posCir1 -= 2;
            pl1pos = 75;
        }
        if(pl1pos >= 100)
        {
            pl1xpos = 40; pl1ypos = 40;
            posCir1 = 10;
            pl1pos = 100;
        }
        pl1t = false;
        pl2t = true;
        if(gaSt)
        {
            gares.setText("Player 2 turn");
        }
    }
    public void movePlayer2()
    {
        if (pl1xpos == 40 && pl1ypos == 40)
        {
            pl1xpos = 40;
            pl1ypos = 40;
        }
        for (int i = 0; i < ra; i++)
        {
            if (posCir2 % 2 == 1)
            {
                pl2xpos += 80;
            }
            if (posCir2 % 2 == 0)
            {
                pl2xpos -= 80;
            }
            if (pl2xpos > 760)
            {
                pl2ypos -= 80;
                pl2xpos -= 80;
                posCir2++;
            }
            if (pl2xpos < 40)
            {
                pl2ypos -= 80;
                pl2xpos += 80;
                posCir2++;
            }
            if (pl2xpos < 30 || pl2ypos < 30 || pl2pos == 100)
            {
                pl2xpos = 40;
                pl2ypos = 40;
                gares.setTranslateX(530);
                gares.setText("Player 2 Won");
                gamBut.setText("Start Again");
                gaSt = false;
            }
        }
        moveNewPlayer2();
    }
    // New positions of player2 for Snakes and Ladders
    public void moveNewPlayer2()
    {
        if(pl2pos == 3)
        {
            pl2xpos = 120; pl2ypos = 520;
            posCir2 += 3;
            pl2pos = 39;
        }
        if(pl2pos == 10)
        {
            pl2xpos = 680; pl2ypos = 680;
            posCir2 += 1;
            pl2pos = 12;
        }
        if(pl2pos == 27)
        {
            pl2xpos = 600; pl2ypos = 360;
            posCir2 += 3;
            pl2pos = 53;
        }
        if(pl2pos == 56)
        {
            pl2xpos = 280; pl2ypos = 120;
            posCir2 += 3;
            pl2pos = 84;
        }
        if(pl2pos == 61 || pl2pos == 99)
        {
            pl2xpos = 120; pl2ypos = 40;
            posCir2 += 3;
            pl2pos = 99;
        }
        if(pl2pos == 72)
        {
            pl2xpos = 760; pl2ypos = 120;
            posCir2 += 1;
            pl2pos = 90;
        }
        if(pl2pos == 16)
        {
            pl2xpos = 600; pl2ypos = 680;
            pl2pos = 13;
        }
        if(pl2pos == 31)
        {
            pl2xpos = 280; pl2ypos = 760;
            posCir2 -= 3;
            pl2pos = 4;
        }
        if(pl2pos == 47)
        {
            pl2xpos = 360; pl2ypos = 600;
            posCir2 -= 2;
            pl2pos = 25;
        }
        if(pl2pos == 63)
        {
            pl2xpos = 40; pl2ypos = 360;
            posCir2 -= 1;
            pl2pos = 60;
        }
        if(pl2pos == 66)
        {
            pl2xpos = 680; pl2ypos = 360;
            posCir2 -= 1;
            pl2pos = 52;
        }
        if(pl2pos == 97)
        {
            pl2xpos = 440; pl2ypos = 200;
            posCir2 -= 2;
            pl2pos = 75;
        }
        if(pl2pos >= 100)
        {
            pl2xpos = 40; pl2ypos = 40;
            posCir2 = 10;
            pl2pos = 100;
        }
        pl2t = false;
        pl1t = true;
        if(gaSt)
        {
            gares.setText("Player 1 turn");
        }
    }
    public void translatePlayer(int x, int y, Circle b)
    {
        TranslateTransition anim = new TranslateTransition(Duration.millis(1000), b);
        anim.setToX(x);
        anim.setToY(y);
        anim.setAutoReverse(false);
        anim.play();
    }
    public void start(Stage primaryStage) throws Exception
    {
        Scene sc = new Scene(createContent());
        primaryStage.setTitle("Snake and Ladder");
        primaryStage.setResizable(false);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
