package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void decreaseQuality(){
        this.quality --;
        if (this.quality <= 0) this.quality = 0;
    }

    public void increaseQuality(){
        if(this.quality <= 0) this.quality = 0;
        this.quality ++;
        if(this.quality >=50) this.quality = 50;
    }

    public String isType(){
        if(this.name.startsWith("Conjured")){ return "Conjured"; }
        else if(this.name.equals("Aged Brie")){ return "Cheese";}
        else if( this.name.equals("Backstage passes to a TAFKAL80ETC concert"))
        { return "Concert";}
        else if ( this.name.equals("Aging Red Wine")){ return "Wine";}
        else if ( this.name.equals("Sulfuras, Hand of Ragnaros")){ return "Legendary";}
        else {
            return "Normal";
        }
    }

    public void updateConjured(){
        this.decreaseQuality();
        this.decreaseQuality();
        if(this.sellIn < 0){
            this.decreaseQuality();
            this.decreaseQuality();
        }
    }

    public void updateCheese(){
        this.increaseQuality();
        if(this.sellIn < 0){
            this.increaseQuality();
        }
    }

    public void updateConcert(){
        this.increaseQuality();
        if(this.sellIn<= 10) this.increaseQuality();
        if(this.sellIn<= 5) this.increaseQuality();
        if(this.sellIn < 0) this.quality = 0;

    }

    public void updateLegendary(){
        this.sellIn = this.sellIn + 1;
    }

    public void decrementSelling(){
        this.sellIn = this.sellIn - 1;
    }

    public void updateNormal(){
        this.decreaseQuality();
        if(this.sellIn <0){
            this.decreaseQuality();
        }
    }

    public void updateWine(){
        if(this.sellIn <-100){
            this.decreaseQuality();
        }
        else if(this.sellIn <0){
            this.increaseQuality();
        }
    }


    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
