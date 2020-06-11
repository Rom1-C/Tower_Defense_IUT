// This  class represents the battlefield with the IDs of the tiles used
package model;

import model.enemy.Enemy;
import model.projectile.Projectile;
import model.turret.Turret;
import exception.TerrainLoaderException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Battlefield {
	
	private Terrain terrain;
	private ObservableList<Enemy> enemyList;
	private ObservableList<Turret> turretList;
	private Graph graph;
	private ObservableList<Projectile> projectileList;
	private int turn_number;
	private IntegerProperty money;
	private IntegerProperty hp;
 
	public Battlefield(String path) throws TerrainLoaderException {
		this.terrain = new Terrain(path);
		money = new SimpleIntegerProperty();
		this.money.set(100);
		hp = new SimpleIntegerProperty();
		this.hp.set(10);
		this.enemyList = FXCollections.observableArrayList();
		this.turretList = FXCollections.observableArrayList();
		this.projectileList = FXCollections.observableArrayList();
		this.graph = new Graph(this.terrain);
		this.graph.createBFS();
		this.turn_number=0;
	}

	public void turnLoop() {
		this.turn_number++;
		System.out.println(this.projectileList);
		for (int i = getEnemyList().size() - 1 ; i >= 0 ; i--) {
			this.enemyList.get(i).action();
		}

		for (int i = getTurretList().size() - 1 ; i >= 0 ; i--) {
			this.turretList.get(i).action();
		}
	}
	
	public int getTurnNumber() {
		return this.turn_number;
	}

	public void addEnemy(Enemy enemy) {
		this.enemyList.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.enemyList.remove(enemy);
	}
	
	public void addTurret(Turret t) {
		this.turretList.add(t);
	}
	
	public void removeTurret(Turret t) {
		this.turretList.remove(t);
	}
	
	public void addProjectile(Projectile projectile) {
		this.projectileList.add(projectile);
	}
	
	public void removeProjectile(Projectile projectile) {
		this.projectileList.remove(projectile);
	}
	
	public Terrain getTerrain() {
		return this.terrain;
	}
		
	public ObservableList<Enemy> getEnemyList(){
		return this.enemyList;
	}
	
	public ObservableList<Turret> getTurretList(){
		return this.turretList;
	}
	
	public ObservableList<Projectile> getProjectileList() {
		return this.projectileList;
	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public void gainMoney(int value) {
		if (value > 0) {
			this.money.set(this.money.intValue()+value);
		}
	}
	
	public boolean buy(int value) {
		if (value > 0) {
			if (this.money.intValue()-value >= 0) {
				this.money.set(this.money.intValue()-value);
				return true;
			}
		}
		return false;
	}
	
	public int getMoney() {
		return this.money.intValue();
	}
	
	public IntegerProperty getMoneyProperty() {
		return this.money;
	}
	
	public void removeHp() {
		this.hp.set(this.hp.intValue()-1);
	}
	
	public IntegerProperty getHpProperty() {
		return this.hp;
	}
	
	public int getHp() {
		return this.hp.intValue();
	}
}
