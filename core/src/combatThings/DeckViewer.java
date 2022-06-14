package combatThings;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Combat;
import com.mygdx.game.HitBox;

import cardThings.Card;
/**
 * @author John Chastanet and Seth Roper.
 * this class handles the showing of cards in drawpiles, decks, or discard piles.
 *
 */
public class DeckViewer implements InputProcessor {
	
	// the character this deck belongs to.
	protected HashMap<Integer, Card> cards;
	
	// this stores the lowest possible y value you can scroll to.
	protected float bottomY;
	
	protected float topY;
	

	
	// if the the card's positions have been initialized.
	private boolean initalized;
	
	/**
	 * @param combat the current combat
	 * @param character the character which the cards belong to.
	 */
	public DeckViewer(HashMap <Integer,Card> cards)
	{
		
		this.cards = cards;

		//this.setCardPositions();
		this.topY = 646;
		this.bottomY = 446;
		
	}
	
	/**
	 *  shows the cards in the deck.
	 */
	public void showCards(SpriteBatch batch)
	{	
		Gdx.input.setInputProcessor(this);
		
		if(this.initalized)	{

			for(int i = 0; i < this.cards.size(); i++) {
				this.cards.get(i).updatePosition(true, 0);
				this.cards.get(i).getDisplay().draw(batch);
			}
		}
		
		else
		{
			this.setCardPositions();
		}
		
		
		
		

		
		
	}
	
	/**
	 * helper method that sets the positions of the cards that will be viewed.
	 */
	public void setCardPositions()
	{
		HashMap<Integer, Card> pile = this.cards;
		
	
		for(int i = 0; i < pile.size(); i++)
		{
			if(i % 5 == 0)
			{
				pile.get(i).getDisplay().setSize(196,196);
				pile.get(i).getDisplay().setCenter(450, ((i) / 5 *  -300) + this.topY);
			
			}
			else if(i % 5 == 1)
			{
				pile.get(i).getDisplay().setSize(196,196);
				pile.get(i).getDisplay().setCenter(650, ((i) / 5 * -300) + this.topY);
		
			}
			else if(i % 5 == 2)
			{
				pile.get(i).getDisplay().setSize(196,196);
				pile.get(i).getDisplay().setCenter(850, ((i) / 5 * -300) + this.topY);
			}
			else if(i % 5 == 3)
			{
				pile.get(i).getDisplay().setSize(196,196);
				pile.get(i).getDisplay().setCenter(1050, ((i) / 5 * -300) + this.topY);
			}
			else if(i % 5 == 4)
			{
				pile.get(i).getDisplay().setSize(196,196);
				pile.get(i).getDisplay().setCenter(1250, ((i) / 5 * -300) + this.topY);
			}
			
		}
		
		
		
		for (int i = 0; i < pile.size(); i++) {
			pile.get(i).setDestination(new Vector3(Combat.getSpriteCenterX(pile.get(i).getDisplay()), Combat.getSpriteCenterY(pile.get(i).getDisplay()), 0));
		}
			this.initalized = true;
			
	}
	
	public void stopShowingCards()
	{
		this.initalized = false;
	}
	
	public void update(HashMap<Integer, Card> cards)
	{
		this.cards = cards;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
 
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		
		if (amount > 0) {
			for(int i = 0; i < this.cards.size(); i++)
			{
				this.cards.get(i).getDisplay().translateY(10);
			}
		}
		if (amount < 0) {
			for(int i = 0; i < this.cards.size(); i++)
			{
				this.cards.get(i).getDisplay().translateY(-10);
			}
		}
		if(amount > 0 && this.cards.get(this.cards.size() - 1).getDestination().y <= this.bottomY)
		{
			for(int i = 0; i < this.cards.size(); i++)
			{
				this.cards.get(i).setDestination(new Vector3(this.cards.get(i).getDestination().x, this.cards.get(i).getDestination().y + Gdx.graphics.getDeltaTime()*10000, 0));
			}
		}
		else if(amount < 0 && this.cards.get(0).getDestination().y > this.topY )
		{
			for(int i = 0; i < this.cards.size(); i++)
			{
				this.cards.get(i).setDestination(new Vector3(this.cards.get(i).getDestination().x, this.cards.get(i).getDestination().y - Gdx.graphics.getDeltaTime()*10000, 0));
			}
		}
		return true;
	}
	}



