package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    final static Logger logger = LoggerFactory.getLogger(GildedRose.class);
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {

                try {
                    NoLegendaryProduct(item);
                }
                catch (Exception e){
                    logger.debug("error on: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
                }

            }
        }
    }

    public void NoLegendaryProduct(Item item){

        SellInDown(item);

        if (item.name.equals("Aged Brie")
              || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = QualityUp(item);
            item.quality = IfConcertOver(item);
        }
        else{
            item.quality = QualityDown(item);
        }
    }

    public Item[] getItems() {
        return items;
    }

    public int QualityUp(Item item){
        if (item.quality < 50) {
            item.quality ++;

            // si c'est une place de concert
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                // si le nombre d'item est inf à 11, +1 +1
                if (item.sellIn < 11 && item.quality < 50) {
                        item.quality ++;
                }
                // si le nombre d'item est inf à 6, +1 +1 +1
                if (item.sellIn < 6 && item.quality < 50) {
                        item.quality ++;
                }
            }
            else if (item.sellIn < 0){
                item.quality ++;
            }
        }
        return item.quality;
    }

    public int QualityDown(Item item) {
        int variation_quality = 0;
        if (item.quality > 0) {
            variation_quality--;
            if (item.name.contains("Conjured")){
                variation_quality *= 2;
            }
            if(item.sellIn < 0){
                variation_quality *= 2;
            }
        }
        if(item.quality + variation_quality < 0){
            return 0;
        }
        return item.quality + variation_quality;
    }

    public int SellInDown(Item item){
            return item.sellIn --;
    }

    public int IfConcertOver(Item item){
        if ((item.name.equals("Backstage passes to a TAFKAL80ETC concert")) && (item.sellIn < 0)){
            return 0;
        }
        return item.quality;
    }
}
