package TrivialPursuit.view.game;

import TrivialPursuit.model.TrivialPursuitController;
import TrivialPursuit.view.create.CreateGameView;
import TrivialPursuit.view.create.CreateGamePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GamePresenter {
    private TrivialPursuitController model;
    private GameView view;

    public GamePresenter(
            TrivialPursuitController model,
            GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getRollDiceButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updatePlayerPosition();

                // Handle dice roll
                // Update player position
                // Check for question space
                // etc.
            }
        });

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateGameView createGameView = new CreateGameView();
                CreateGamePresenter createGamePresenter = new CreateGamePresenter(model, createGameView);
                createGamePresenter.addWindowEventHandlers();
                view.getScene().setRoot(createGameView);
                createGameView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {
        // Update view with current game state
        // - Current player
        // - Player positions
        // - Scores
        // etc.
    }

    public void addWindowEventHandlers() {

    }

    private int playerPositionIndex = 0;


    private void updatePlayerPosition() {
        int[][] coordinaten = {
                {270, 271}, {195, 230}, {146, 200}, {99, 170}, {47, 142}, {86, 90}, {141, 48}, {200, 24}, {269, 13},
                {334, 24}, {392, 48}, {444, 90}, {490, 144}, {515, 207}, {523, 264}, {514, 335}, {490, 396}, {447, 449},
                {402, 489}, {336, 516}, {270, 527}, {201, 517}, {138, 490}, {87, 447}, {42, 398}, {20, 332}, {14, 263},
                {20, 200}, {195, 306}, {144, 340}, {99, 365}, {271, 344}, {265, 406}, {262, 468}, {334, 306}, {387, 343},
                {429, 366}, {338, 226}, {390, 196}, {441, 171}, {266, 71}, {271, 134}, {272, 188}
        };

        // Haal de nieuwe x en y op
        int x = coordinaten[playerPositionIndex][0];
        int y = coordinaten[playerPositionIndex][1];

        // Update de pionpositie in de view
        view.updatePawn(x, y);

        // Ga naar de volgende positie
        playerPositionIndex++;

        // Reset index als hij bij het einde is (optioneel, afhankelijk van je spelregels)
        if (playerPositionIndex >= coordinaten.length) {
            playerPositionIndex = 0;
        }
    }

}