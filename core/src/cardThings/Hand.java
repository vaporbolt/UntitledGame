package cardThings;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

public class Hand  {

	// makes so you can't really reshuffle your hand cause that would be dumb.

	protected HashMap<Integer, Card> cards = new HashMap<Integer, Card>();
	protected HorizontalGroup group;   
	protected int size = this.cards.size();
	final float CARD_XDISTANCE = 80;
	final float CARD_YDISTANCE = 5;
	final float ROTATION = 2;
   
   /**
 * @return a specific card from your hand.
 */
   public Card get(Integer key)
   {
	   return this.cards.get(key);
   }
   
   /**
 * @return the size of the hand
 */
   public int getSize() {
	   return size;
   }

   /**
 * @param newSize - the new Size of za Hando
 */
public void setSize(int newSize) {
	   size = newSize;
   }

   /**
 * @param card the card to be added to the hand.
 * adds the card to the hand and then moves the cards appropriately.
 * 
 */
   public void addCard(Card card)
   {
	   
	   this.cards.put((Integer)this.cards.size(), card);
	   
   }
   
   public Card removeCard(Integer key) {
			Card buh = this.cards.get(key);
			for(int i = 0; i < this.cards.size(); i++) {
				Integer temp = i - 1;
				if( i > key) {
					this.cards.replace(temp, this.cards.get((Integer)i));
				}
			}
					
			this.cards.remove(this.cards.size() - 1);
			
			return buh;
			   
	}
   
  
 /**
 * @param batch the sprite batch to be drawn to
 * @param y the y position the cards should drawn at.
 */
   public void setHandDestination(SpriteBatch batch, float y) {
	    if (size == 0) {return;}
		int ind = size - 1;
		float[][] bruh;
		if (size%2 == 0) {
			float[][] notBruhIGuess = {{CARD_XDISTANCE*1, CARD_YDISTANCE*0, ROTATION*1},
							  {CARD_XDISTANCE*3, CARD_YDISTANCE*2, ROTATION*2},
							  {CARD_XDISTANCE*5, CARD_YDISTANCE*4, ROTATION*3},
							  {CARD_XDISTANCE*7, CARD_YDISTANCE*6, ROTATION*4}};
			bruh = notBruhIGuess;
		}
		else {
			float[][] notBruhIGuess = {{CARD_XDISTANCE*2, CARD_YDISTANCE*1, ROTATION*1},
							  {CARD_XDISTANCE*4, CARD_YDISTANCE*3, ROTATION*2},
							  {CARD_XDISTANCE*6, CARD_YDISTANCE*5, ROTATION*3},
							  {CARD_XDISTANCE*8, CARD_YDISTANCE*8, ROTATION*4}};
			bruh = notBruhIGuess;
		}

		for (int i = size/2 - 1; i >= 0; i--) {
			this.cards.get(ind).setDestination(new Vector3(800f + bruh[i][0] - size*5*(i + 1), y - bruh[i][1], -1 * bruh[i][2]));
			ind--;
		}

		if (size%2 != 0) {
			this.cards.get(ind).setDestination(new Vector3(800f, y, 0));
			ind--;
		}

		for (int i = 0; i <= size/2 - 1; i++) {
			this.cards.get(ind).setDestination(new Vector3(800f - bruh[i][0] + size*5*(i + 1), y - bruh[i][1], bruh[i][2]));
			ind--;
		}
		
		
	}
}

   

