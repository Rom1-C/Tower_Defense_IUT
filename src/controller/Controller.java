package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.*;
import model.enemy.Enemy;
import model.enemy.Quartz;
import view.BattlefieldView;

public class Controller implements Initializable{	
	@FXML
	private TilePane tilepane;
	@FXML
    private Pane pane;
	private Battlefield battlefield;
    private BattlefieldView battlefieldView;
    

    public void initialize(URL arg0, ResourceBundle arg1) {
    	battlefield = new Battlefield("battlefields/battlefield1.json");
    	battlefieldView = new BattlefieldView(battlefield, tilepane);
    	battlefieldView.createView();
    	battlefield.getEnemyList().addListener(new EnemyListListener(tilepane,battlefield,battlefieldView));
    	Graph g = new Graph(battlefield);
    	g.createGraph();
    	g.BFS();
    }
    
    public void waveLoop() {
    	while(true) {

    		for (int i = 0 ; i < battlefield.getEnemyList().size() ; i++) {
    			battlefield.getEnemyList().get(i).action();
    		}
    		for (int i = 0 ; i < battlefield.getTurretList().size() ; i++) {
    			battlefield.getTurretList().get(i).action();
    		}
    	}
    }
    
    public void createQuartz() {
    	Enemy q = new Quartz(0,0);
    	q.add(battlefield);
    }

}
