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
                logger.debug("if not Sulfuras: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
                NoLegendaryProduct(item);
            }
        }
    }

    public void NoLegendaryProduct(Item item){

        SellInDown(item);

        if (item.name.equals("Aged Brie")
              || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = QualityUp(item);
            item.quality = IfConcertOver(item);
            logger.debug("if Brie or Stage: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
        }
        else{
            item.quality = QualityDown(item);
            logger.debug("if not Brie or Stage: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
        }
    }

    public Item[] getItems() {
        return items;
    }

    public int QualityUp(Item item){
        if (item.quality < 50) {
            item.quality ++;
            logger.debug("quality++ : item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);

            // si c'est une place de concert
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                // si le nombre d'item est inf à 11, +1 +1
                if (item.sellIn < 11 && item.quality < 50) {
                    item.quality ++;
                    logger.debug("quality++: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
                }
                // si le nombre d'item est inf à 6, +1 +1 +1
                if (item.sellIn < 6 && item.quality < 50) {
                    item.quality ++;
                    logger.debug("quality++: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
                }
            }
            else if (item.sellIn < 0){
                item.quality ++;
                logger.debug("quality++: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
            }
        }
        return item.quality;
    }

    public int QualityDown(Item item) {
        int variation_quality = 0;
        if (item.quality > 0) {
            variation_quality--;
            logger.debug("quality--: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
            //if (item.name.contains("Conjured") && !(item.name.contains("Like")) && !(item.name.contains("Almost"))){
            if (item.name.equals("Conjured Mana Cake")){
                variation_quality *= 2;
                logger.debug("quality--*2: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
            }
            if(item.sellIn < 0){
                variation_quality *= 2;
                logger.debug("quality--*2: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
            }
        }
        if(item.quality + variation_quality < 0){
            return 0;
        }
        return item.quality + variation_quality;
    }

    public int SellInDown(Item item){
        logger.debug("sillIn--: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
        return item.sellIn --;
    }

    public int IfConcertOver(Item item){
        if ((item.name.equals("Backstage passes to a TAFKAL80ETC concert")) && (item.sellIn < 0)){
            return 0;
        }
        logger.debug("not a Stage: item " + item.name + " - sellIn " + item.sellIn + " - quality " + item.quality);
        return item.quality;
    }
}
