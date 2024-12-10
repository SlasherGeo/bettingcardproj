
import javafx.animation.*;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class JavaFXTemplate extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("K E N O");


        //setting up the start screen
        BackgroundFill bgfill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        BorderPane root = new BorderPane();
        root.setBackground(new Background(bgfill));

        //where I'm going to be putting the elements of the start screen
        VBox menuThings = new VBox(50);
        VBox startThing = new VBox();


        //for the menu on top
        Menu options = new Menu("Options");
        MenuItem ruleMenu = new MenuItem("Rules");
        MenuItem oddsMenu = new MenuItem("Odds");
        MenuItem exitMenu = new MenuItem("Exit");
        MenuItem changeLook = new MenuItem("Change Look");
        MenuBar menus = new MenuBar(options);
        options.getItems().add(ruleMenu);
        options.getItems().add(oddsMenu);
        options.getItems().add(exitMenu);
        options.getItems().add(changeLook);
        changeLook.setDisable(true);



        Label startText = new Label("Welcome to Keno!");

        //the start button settings
        Button start = new Button("Start!");
        start.setPrefSize(500, 150);
        start.setFont(new Font("Garamond", 40));
        start.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));


        //text settings for e
        startText.setAlignment(Pos.BOTTOM_CENTER);
        startText.setFont(new Font("Source Code Pro", 50));


        //for the top menu
        menuThings.getChildren().add(menus);
        menuThings.getChildren().add(startText);
        menuThings.setAlignment(Pos.CENTER);

        //for the start button
        startThing.getChildren().add(start);
        startThing.setAlignment(Pos.TOP_CENTER);

        //image setup
        //System.out.println();
        Image keno = new Image(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/kenoStartImage.jpg"));
        ImageView kView = new ImageView(keno);


        //borderpane stuff
        root.setTop(menuThings);
        root.setCenter(kView);
        root.setBottom(startThing);


        Scene scene = new Scene(root, 900, 850);

        primaryStage.setScene(scene);
        primaryStage.show();

        //displays the odds page
        oddsMenu.setOnAction(actionEvent -> {
            //background for the odds display page
            showOdds(primaryStage, scene);
        });
        //shows the rule page of the program.
        ruleMenu.setOnAction(actionEvent -> {
            //setting up to put stuff into rule page
            showRules(primaryStage, scene);

        });
        //exits the game
        exitMenu.setOnAction(actionEvent -> System.exit(0));

        //little animation for start button
        start.setOnMouseEntered(mouseEvent -> start.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY))));
        start.setOnMouseExited(mouseEvent -> start.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY))));


        //BetCard class created - holds crucial values of the game
        BetCard bc = new BetCard();
        //where the gameplay starts
        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {


                //changing the rules and odds buttons so that it returns to the game

                //set the background for the game
                BorderPane gamePane = new BorderPane();
                BackgroundFill gameBG = new BackgroundFill(Color.ROYALBLUE, CornerRadii.EMPTY, Insets.EMPTY);
                gamePane.setBackground(new Background(gameBG));

                changeLook.setDisable(false);





                //to put the gameplay elements in
                VBox gameBox = new VBox();



                //this is for how many spots the user wants to play
                Button one = new Button("One");
                Button four = new Button("Four");
                Button eight = new Button("Eight");
                Button ten = new Button("Ten");

                //reset button for the user to change their selections.
                Button resetButton = new Button("Reset");
                resetButton.setDisable(true);
                resetButton.setAlignment(Pos.CENTER);




                Button pickForMe = new Button("Pick for me!");
                pickForMe.setAlignment(Pos.CENTER);

                //sizes for the spot buttons
                one.setPrefSize(40, 40);
                four.setPrefSize(40, 40);
                eight.setPrefSize(45, 40);
                ten.setPrefSize(40, 40);

                pickForMe.setPrefSize(90, 50);
                pickForMe.setDisable(true);


                //button setup for the amount of drawings the player wants
                Button drawOne = new Button("1");
                Button drawTwo = new Button("2");
                Button drawThree = new Button("3");
                Button drawFour = new Button("4");

                //sizes for the drawings buttons
                drawOne.setPrefSize(50, 50);
                drawTwo.setPrefSize(50, 50);
                drawThree.setPrefSize(50, 50);
                drawFour.setPrefSize(50, 50);

                Button startDraw = new Button("DRAW!");
                startDraw.setDisable(true);
                startDraw.setPrefSize(250, 100);
                startDraw.setFont(new Font("Times New Roman", 30));


                HBox spotBox = new HBox(one, four, eight, ten);
                HBox drawBox = new HBox(drawOne, drawTwo, drawThree, drawFour);
                VBox groupBox = new VBox(resetButton, drawBox);
                HBox bottomBox = new HBox(groupBox, startDraw);


                spotBox.setAlignment(Pos.CENTER);
                drawBox.setAlignment(Pos.BOTTOM_CENTER);
                groupBox.setAlignment(Pos.CENTER);
                bottomBox.setAlignment(Pos.CENTER);


                //labels for the game screen
                Label titleGame = new Label("K E N O");
                Label instructSpot = new Label("Pick how many spots you want to play.");
                Label instructDraw = new Label("Pick how many drawings you want to be played, or click Reset to start over.");
                Label spotsChosen = new Label ("This is where the numbers you have chosen will appear.");

                //settings for the labels
                //for the title
                titleGame.setFont(new Font("Impact", 40));
                titleGame.setStyle("-fx-text-fill: WHITE");

                //for the instructions on how to play
                instructSpot.setFont(new Font("Source Code Pro", 25));
                instructSpot.setStyle("-fx-text-fill: WHITE");


                instructDraw.setFont(new Font("Source Code Pro", 20));
                instructDraw.setStyle("-fx-text-fill: WHITE");

                spotsChosen.setFont(new Font("Source Code Pro", 15));
                spotsChosen.setStyle("-fx-text-fill: WHITE");

                //settings up the bet card
                GridPane buttons = new GridPane();
                buttons.setAlignment(Pos.CENTER);

                int count = 1;
                //creating 1-80 buttons
                //to store the buttons for access
                ArrayList<Button> gridButtons = new ArrayList<>();

                //creating 1-80 buttons
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 10; j++) {
                        Button repetitive = new Button(Integer.toString(count));
                        repetitive.setPrefSize(60, 60);
                        repetitive.setDisable(true);
                        buttons.add(repetitive, j, i);
                        gridButtons.add(repetitive);
                        count++;
                    }
                }


                //putting everything onto the screen
                //layout of the screen:
                //menu
                //game title
                //instructions for user
                //the spots for the user to choose
                //the numbers they have picked
                //a button for the computer to choose for them
                //the 1-80 buttons to choose from
                //instructions to pick how many drawings they want to do
                //the buttons to pick how many drawings they want to do
                //a reset button to change their selections
                //a button to start the drawings
                gameBox.getChildren().add(menus);
                gameBox.getChildren().add(titleGame);
                gameBox.getChildren().add(instructSpot);
                gameBox.getChildren().add(spotBox);
                gameBox.getChildren().add(spotsChosen);
                gameBox.getChildren().add(pickForMe);
                gameBox.getChildren().add(buttons);
                gameBox.getChildren().add(instructDraw);
                gameBox.getChildren().add(bottomBox);

                gameBox.setAlignment(Pos.TOP_CENTER);
                gamePane.setTop(gameBox);

                if(bc.getFlagColor() == 1){
                    changey(gamePane, titleGame, instructSpot, instructDraw, spotsChosen, gridButtons, one, four, eight, ten, drawOne, drawTwo, drawThree, drawFour);
                }
                Scene gameScene = new Scene(gamePane, 900, 850);
                //for when we come back here after the player continues
                reset(instructSpot, instructDraw, spotsChosen, bc, gridButtons, one, four, eight, ten, startDraw, resetButton);
                primaryStage.setScene(gameScene);
                primaryStage.show();

                ruleMenu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        showRules(primaryStage, gameScene);
                    }
                });
                oddsMenu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        showOdds(primaryStage, gameScene);
                    }
                });

                //event handlers for all gameplay elements

                //for the numbered ones - settings up the spots, and if any of them are pressed,
                //they disable one another, then calls the playGrid to unlock the grid.
                one.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        disableSpotButtons(one, four, eight, ten);

                        bc.setSpotsPlayed(1);
                        pickForMe.setDisable(false);
                        instructSpot.setText("You chose: " + one.getText() + " spot to play");
                        playGrid(gridButtons, bc, spotsChosen, pickForMe);
                    }
                });
                four.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        disableSpotButtons(one, four, eight, ten);
                        bc.setSpotsPlayed(4);
                        pickForMe.setDisable(false);

                        instructSpot.setText("You chose: " + four.getText() + " spots to play");
                        playGrid(gridButtons, bc, spotsChosen, pickForMe);
                    }
                });
                eight.setOnAction(actionEvent1 -> {
                    disableSpotButtons(one, four, eight, ten);
                    bc.setSpotsPlayed(8);
                    pickForMe.setDisable(false);
                    instructSpot.setText("You chose: " + eight.getText() + " spots to play");
                    playGrid(gridButtons, bc, spotsChosen, pickForMe);

                });
                ten.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        disableSpotButtons(one, four, eight, ten);

                        bc.setSpotsPlayed(10);
                        pickForMe.setDisable(false);
                        instructSpot.setText("You chose: " + ten.getText() + " spots to play");
                        playGrid(gridButtons, bc, spotsChosen, pickForMe);
                    }
                });
                //randomly pick numbers based on the number of spots they've chosen.
                pickForMe.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        //randomizes what the user has picked and disables all the grid buttons
                        bc.pickME();
                        spotsDisplay(spotsChosen, bc);
                        disableButton(gridButtons);
                        pickForMe.setDisable(true);
                    }
                });

                //drawingButtons actions
                //when clicked, it sets the BetCard drawing number equal to what they pressed
                drawOne.setOnAction(actionEvent14 -> {
                    bc.setDrawings(Integer.parseInt(drawOne.getText()));
                    drawButtons(gridButtons, startDraw, resetButton, bc);
                    instructDraw.setText("You picked " + drawOne.getText() + " drawing");
                });
                drawTwo.setOnAction(actionEvent13 -> {
                    bc.setDrawings(Integer.parseInt(drawTwo.getText()));
                    drawButtons(gridButtons, startDraw, resetButton, bc);
                    instructDraw.setText("You picked " + drawTwo.getText() + " drawings");
                });

                drawThree.setOnAction(actionEvent12 -> {
                    bc.setDrawings(Integer.parseInt(drawThree.getText()));
                    drawButtons(gridButtons, startDraw, resetButton, bc);
                    instructDraw.setText("You picked " + drawThree.getText() + " drawings");
                });
                drawFour.setOnAction(actionEvent15 -> {
                    bc.setDrawings(Integer.parseInt(drawFour.getText()));
                    drawButtons(gridButtons, startDraw, resetButton, bc);
                    instructDraw.setText("You picked " + drawFour.getText() + " drawings");
                });

                //resets the player's choices, the labels
                resetButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        reset(instructSpot, instructDraw, spotsChosen, bc, gridButtons, one, four, eight, ten, startDraw, resetButton);
                    }
                });

                //changes the looks of all the variables used within the gameplay gui
                changeLook.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        changey(gamePane, titleGame, instructSpot, instructDraw, spotsChosen, gridButtons, one, four, eight, ten, drawOne, drawTwo, drawThree, drawFour);
                        bc.setFlagColor(1);
                    }
                });

                startDraw.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent actionEvent) {
                        //creating the results screen
                        BackgroundFill bgResult = new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY);
                        BorderPane resultsBP = new BorderPane();
                        resultsBP.setBackground(new Background(bgResult));
                        GridPane resultsGP = new GridPane();
                        resultsGP.setAlignment(Pos.CENTER);

                        //this is for the animation - so in the general area where the animation happens
                        //the button won't be clickable until after the animation is done
                        //same thing for continue button
                        startDraw.setDisable(true);

                        //this will be the button used to continue - not the same as startDraw
                        //continues to the next screen
                        Button continueButton = new Button("Continue");
                        continueButton.setFont(new Font("Times New Roman",30));
                        continueButton.setPrefSize(250, 100);
                        continueButton.setDisable(true);

                        //where to put all the elements
                        VBox resultBox = new VBox();
                        HBox userBox = new HBox();
                        userBox.setAlignment(Pos.CENTER);
                        Label resultTitle = new Label("THE RESULTS");
                        Label userTitle = new Label("Your choices: ");
                        Label prizeTitle = new Label();

                        resultTitle.setFont(new Font("Impact", 50));
                        userTitle.setFont(new Font("Verdana", 30));
                        userTitle.setStyle("-fx-font-style: italic");

                        //obtain the user's selections to compare
                        Object[] userSet = bc.getSpotArray().toArray();
                        Set<Integer> set1 = bc.getSpotArray();
                        Set<Integer> set2 = bc.resultOfDraw();

                        //intersection is overwritten when retainAll is called every time, making it the intersection of
                        //the results and the user's choices

                        Set<Integer> intersection = new LinkedHashSet<>(set1);

                        intersection.retainAll(set2);
                        //the size = how many matched
                        int prize = bc.prize(intersection.size());
                        prizeTitle.setText("You've won $" + prize + " for this draw!");
                        prizeTitle.setFont(new Font("Times New Roman", 30));


                        //retrieve the random numbers with no duplicates and store into array for easier access
                        Object[] resultSet = bc.resultOfDraw().toArray();




                        //settings up a grid of the numbers from the result of the draw
                        //fitted with animations to slowly reveal all the numbers
                        int resultCount = 0;
                        int milly = 500;
                        for(int i = 0; i < 4; i++){
                            for(int j = 0; j < 5; j++){
                                Label resultSquare = new Label(Integer.toString((Integer) resultSet[resultCount]));
                                resultSquare.setAlignment(Pos.CENTER);
                                resultSquare.setFont(new Font("Courier New", 25));
                                resultSquare.setPrefSize(40, 40);
                                resultSquare.setStyle("-fx-border-color: Gray");
                                //if the integer being put into the label is a matching one
                                if(intersection.contains((Integer) resultSet[resultCount])){
                                    resultSquare.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                                }else {
                                    resultSquare.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                                }
                                FadeTransition ft = new FadeTransition(Duration.millis(milly), resultSquare);
                                ft.setFromValue(0);
                                ft.setToValue(1);
                                ft.play();
                                resultsGP.add(resultSquare, j, i);
                                resultCount++;
                                milly += 500;
                            }

                        }

                        int userMill = 700;
                        //animation for the player's choices
                        for(int i = 0; i < bc.getSpotArray().size(); i++){
                            Label userSquare = new Label(Integer.toString((Integer) userSet[i]));
                            userSquare.setAlignment(Pos.CENTER);
                            userSquare.setFont(new Font("Source Code Pro", 25));
                            userSquare.setPrefSize(40, 40);
                            userSquare.setStyle("-fx-border-color: GREEN");
                            userSquare.setBackground(new Background(new BackgroundFill(Color.TURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
                            FadeTransition userFT = new FadeTransition(Duration.millis(userMill), userSquare);
                            userFT.setFromValue(0);
                            userFT.setToValue(1);
                            SequentialTransition seqTransition = new SequentialTransition (
                                    new PauseTransition(Duration.millis(3000)), // wait a second
                                    userFT
                            );
                            seqTransition.play();
                            userBox.getChildren().add(userSquare);
                        }
                        //clears the set to generate a new set when the draw button is clicked again
                        FadeTransition buttonTrans = new FadeTransition(Duration.millis(1000), startDraw);
                        buttonTrans.setFromValue(0);
                        buttonTrans.setToValue(1);
                        SequentialTransition buttonPause = new SequentialTransition(
                                new PauseTransition(Duration.millis(4000)),
                                buttonTrans
                        );
                        buttonPause.play();
                        buttonPause.setOnFinished(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent actionEvent) {
                                startDraw.setDisable(false);
                            }
                        });
                        //since this is the first draw, subtract one draw
                        bc.setDrawings(bc.getDrawingsNum()-1);




                        //puttings the results screen together
                        resultBox.setAlignment(Pos.CENTER);
                        resultsBP.setTop(menus);
                        resultBox.getChildren().add(resultTitle);
                        resultBox.getChildren().add(resultsGP);
                        resultBox.getChildren().add(userTitle);
                        resultBox.getChildren().add(userBox);
                        if(bc.getDrawingsNum() > 0){
                            bc.clearDraw();
                            resultBox.getChildren().add(startDraw);
                        }else{
                            FadeTransition buttonC = new FadeTransition(Duration.millis(1000), startDraw);
                            buttonC.setFromValue(0);
                            buttonC.setToValue(1);
                            SequentialTransition buttonPC = new SequentialTransition(
                                    new PauseTransition(Duration.millis(4000)),
                                    buttonC
                            );
                            buttonPC.play();
                            buttonPC.setOnFinished(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent actionEvent) {
                                    continueButton.setDisable(false);
                                }
                            });
                            resultBox.getChildren().add(continueButton);
                        }
                        resultBox.getChildren().add(prizeTitle);


                        resultsBP.setCenter(resultBox);
                        Scene resultScene = new Scene(resultsBP, 900, 850);
                        primaryStage.setScene(resultScene);
                        primaryStage.show();

                        ruleMenu.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                showRules(primaryStage, resultScene);
                            }
                        });
                        oddsMenu.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                showOdds(primaryStage, resultScene);
                            }
                        });

                        continueButton.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent actionEvent) {
                                BorderPane continueBP = new BorderPane();
                                continueBP.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                                VBox continueBox = new VBox();
                                HBox buttonsBoxH = new HBox();
                                VBox buttonsBoxV = new VBox();
                                continueBox.setAlignment(Pos.CENTER);
                                buttonsBoxH.setAlignment(Pos.CENTER);
                                buttonsBoxV.setAlignment(Pos.CENTER);

                                Label continueText = new Label("Would you like to play again?");
                                Label totalPrizeText = new Label("You have obtained $" + bc.getPrizeMoney()+ " in total!");

                                continueText.setFont(new Font("Impact", 40));
                                continueText.setStyle("-fx-text-fill: WHITE");
                                totalPrizeText.setFont(new Font("Times New Roman", 25));
                                totalPrizeText.setStyle("-fx-text-fill: WHITE");

                                start.setPrefSize(150, 100);
                                start.setText("Again!");
                                start.setFont(new Font("Times New Roman", 20));


                                Button exitPls = new Button("Exit");
                                exitPls.setPrefSize(150, 100);
                                exitPls.setBackground(start.getBackground());
                                exitPls.setFont(start.getFont());
                                exitPls.setOnMouseEntered(mouseEvent -> exitPls.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY))));
                                exitPls.setOnMouseExited(mouseEvent -> exitPls.setBackground(start.getBackground()));

                                exitPls.setOnAction(actionEvent16 -> System.exit(0));

                                continueBox.getChildren().add(continueText);
                                buttonsBoxH.getChildren().add(start);
                                buttonsBoxH.getChildren().add(exitPls);
                                buttonsBoxV.getChildren().add(totalPrizeText);
                                buttonsBoxV.getChildren().add(buttonsBoxH);


                                continueBP.setTop(continueBox);
                                continueBP.setCenter(buttonsBoxV);
                                Scene gameoverScene = new Scene(continueBP, 900, 850);
                                primaryStage.setScene(gameoverScene);
                                primaryStage.show();

                                ruleMenu.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        showRules(primaryStage, gameoverScene);
                                    }
                                });
                                oddsMenu.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        showOdds(primaryStage, gameoverScene);
                                    }
                                });



                            }
                        });





                    }
                });




            }

        });

    }

    public static void reset(Label instructSpot, Label instructDraw, Label spotsChosen, BetCard bc, ArrayList<Button> gridButtons,
                             Button one, Button four, Button eight, Button ten, Button startDraw, Button resetButton){
        instructSpot.setText("Pick how many spots you want to play.");
        instructDraw.setText("Pick how many drawings you want to be played, or click the Reset button to reset your choices");
        spotsChosen.setText("This is where the numbers you have chosen will appear.");
        //clear BetCard elements
        bc.clearArr();
        bc.setDrawings(0);
        bc.setSpotsPlayed(0);

        //disable the grid, enable the spot buttons
        disableButton(gridButtons);
        one.setDisable(false);
        four.setDisable(false);
        eight.setDisable(false);
        ten.setDisable(false);

        //disable the 'continue' button
        startDraw.setDisable(true);

        //re-disable the reset button
        resetButton.setDisable(true);
    }
    public static void changey(BorderPane bp, Label tG, Label iS, Label iD, Label sC, ArrayList<Button> gB, Button o, Button f, Button e, Button t, Button d1, Button d2, Button d3, Button d4){
        bp.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        tG.setStyle("-fx-text-fill: Black");
        iD.setStyle("-fx-text-fill: Black");
        iS.setStyle("-fx-text-fill: Black");
        sC.setStyle("-fx-text-fill: Black");
        changeButtonColor(o);
        changeButtonColor(f);
        changeButtonColor(e);
        changeButtonColor(t);
        for(Button b : gB){
            changeButtonColor(b);
        }
        changeButtonColor(d1);
        changeButtonColor(d2);
        changeButtonColor(d3);
        changeButtonColor(d4);

    }
    public static void changeButtonColor(Button b){
        b.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        b.setStyle("-fx-text-fill: WHITE");
    }

    public static void spotsDisplay(Label sC, BetCard bc){
        String sP = "";
        Set<Integer> sA = bc.getSpotArray();
        for(int e : sA){
            sP += e + " ";
        }
        sC.setText(sP);
    }
    //drawButtons - checks all the buttons within the GridPane and sees if all are disabled
    //if they aren't, the button to start the drawing will still be disabled
    //if they are, the button will be enabled
    //resetButton follows the same procedure
    public static void drawButtons(ArrayList<Button> gB, Button sD, Button rB, BetCard bc){
        //go through each button and check if they're disabled.
        //if any button is not disabled, the 'continue' button will be disabled
        //if all buttons are disabled, the button will be enabled
        if(bc.getSpotArray().isEmpty()){
            return;
        }
        for(Button a : gB){
            sD.setDisable(!a.isDisabled());
            rB.setDisable(!a.isDisabled());
        }
    }
    //playGrid - once a spotButton has been pressed, the grid is now able to be played.
    public static void playGrid(ArrayList<Button> gridButtons, BetCard bc, Label sC, Button pFM) {

        //unlocks all the buttons within the gridpane
        for (Button gridButton : gridButtons) {
            gridButton.setDisable(false);
        }
        //for every button in the gridPane
        for (Button gridButton : gridButtons) {
            //when a button on the grid is pressed
            gridButton.setOnAction(actionEvent -> {
                //checking if the player has picked their spots
                bc.add(Integer.parseInt(gridButton.getText()));
                gridButton.setDisable(true);
                spotsDisplay(sC, bc);
                if(!pFM.isDisabled()){
                    pFM.setDisable(true);
                }
            });
            //to check if the user has clicked all the spots they picked
            gridButton.setOnMouseClicked(ActionEvent -> {
                if(bc.getSpotsPlaying() == 0){
                    disableButton(gridButtons);
                }
            });
        }

    }

    public static void disableSpotButtons(Button one, Button four, Button eight, Button ten){
        one.setDisable(true);
        four.setDisable(true);
        eight.setDisable(true);
        ten.setDisable(true);


    }
    //disables all the buttons
    public static void disableButton(ArrayList<Button> gB) {
        for (Button butt : gB) {
            butt.setDisable(true);
        }
    }

    //shows the rules page
    public static void showRules(Stage primaryStage, Scene scene) {
        BorderPane rulesBP = new BorderPane();
        BackgroundFill rulesFill = new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY);
        BackgroundFill bgfill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        rulesBP.setBackground(new Background(rulesFill));

        VBox rulesBox = new VBox(40);

        //text for the rule page
        Label rulesTitle = new Label("RULES:");
        Label rulesDesc = new Label((char) 0x2022 + "Pick how many spots you want to play.\n" + (char) 0x2022 + "Pick the numbers in said spots," +
                "\n or have us decide for you.\n" + (char) 0x2022 + "Pick the number of drawings you want (1-4).\n" +
                (char)0x2022 +"Click 'Reset' to reset your selections, \nor press 'DRAW!' to start the draw");

        //to exit the rules page
        Button exitRules = new Button("Got it!");
        exitRules.setPrefSize(400, 150);
        exitRules.setBackground(new Background(bgfill));
        exitRules.setFont(new Font("Garamond", 40));

        //little animation :)
        exitRules.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                exitRules.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        exitRules.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                exitRules.setBackground(new Background(bgfill));
            }
        });

        //exits to the welcome screen
        exitRules.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //reset to start screen
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        //fonts for the labels
        rulesTitle.setFont(new Font("Impact", 50));
        rulesDesc.setFont(new Font("Source Code Pro", 45));

        //putting the elements into the vbox
        rulesBox.setAlignment(Pos.TOP_CENTER);
        rulesBox.getChildren().add(rulesTitle);
        rulesBox.getChildren().add(rulesDesc);
        rulesBox.getChildren().add(exitRules);

        //putting the vbox into the borderpane
        rulesBP.setTop(rulesBox);

        //showing the scene
        Scene ruleScene = new Scene(rulesBP, 900, 850);
        primaryStage.setScene(ruleScene);
        primaryStage.show();
    }

    //shows the odds page
    public static void showOdds(Stage primaryStage, Scene scene) {
        BorderPane oddsBP = new BorderPane();
        BackgroundFill oddsFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
        BackgroundFill bgfill = new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY);
        oddsBP.setBackground(new Background(oddsFill));

        //box for the elements of the page
        VBox oddsBox = new VBox(40);

        //text for the rule page
        Label oddsTitle = new Label("ODDS OF WINNING:");
        Label oddsDesc = new Label("Pick 1 - 1 in 4 chance\n(Win up to $2)\nPick 4 - 1 in 3.86 chance! (Great Value!)\n(Win up to $75)\nPick 8 - 1 in 9.7! chance\n(Win up to $10,000!!)\nPick 10 - 1 in 9.05 chance!\n(WIN UP TO $100000!!!)");

        //setting up button to exit odds
        Button exitOdds = new Button("I like those odds.");
        exitOdds.setFont(new Font("Garamond", 40));
        exitOdds.setPrefSize(400, 150);
        exitOdds.setBackground(new Background(bgfill));

        //little animation :)
        exitOdds.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                exitOdds.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });
        exitOdds.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                exitOdds.setBackground(new Background(bgfill));
            }
        });

        //exiting the page to the welcome screen

        exitOdds.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //reset to start screen
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        //fonts for the labels
        oddsTitle.setFont(new Font("Impact", 50));
        oddsDesc.setFont(new Font("Source Code Pro", 45));

        //putting the elements into the vbox
        oddsBox.setAlignment(Pos.TOP_CENTER);
        oddsBox.getChildren().add(oddsTitle);
        oddsBox.getChildren().add(oddsDesc);
        oddsBox.getChildren().add(exitOdds);

        //putting the vbox into the borderpane
        oddsBP.setTop(oddsBox);

        //showing the scene
        Scene oddsScene = new Scene(oddsBP, 900, 850);
        primaryStage.setScene(oddsScene);
        primaryStage.show();
    }


}
