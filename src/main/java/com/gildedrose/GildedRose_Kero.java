package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*étape 1, vérifier que l'item n'est pas Sulfuras car c'est le seul qui ne bouge pas
étape 2, Faire baisser le sellIn, tout les objets ont le sellIn qui déscend peut importe ce qu'il se passe
étape 3, Modifier la qualité, a=0 b=monte, c=descend. a=Concert terminé b=Brie ou Concert c=tout le reste
étape 3a, Si c'est un Concert avec SellIn<0, la qualité passe à 0 (on a pas besoin de regarder si quality<50
étape 3b, Si la qualité est inférieur à 50, la qualité monte de 1
étape 3b1, Si Concert, monter la qualité suivant le sellIn
étape 3b2, En dehors du concert (else if) si sellIn est inférieur à 0, la qualité monte de 1
étape 3c, Si la qualité est >=0, la qualité descend de 1 (-1)
étape 3c1, Si sellIn est <0, la qualité descend encore de 1 (-2)
étape 3c2, Si Conjured, la qualité descend encore de 1 (-2)
étape 3c2a, Si sellIn est <0 (et Conjured), la qualité descend encore de 1 (-4) (qualité <0, Conjuré, 2*(sellIn<0))*/

public class GildedRose_Kero {
    final static Logger logger = LoggerFactory.getLogger(GildedRose_Kero.class);
    Item_Kero[] items;
    String concert="Backstage passes to a TAFKAL80ETC concert";

    public GildedRose_Kero(Item_Kero[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item_Kero item : items) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                logger.debug("NS: "+item.name+" "+item.sellIn+" "+item.quality);
                SellIn(item);
                NormalItem(item);
            }
        }
    }

    public int SellIn(Item_Kero item){
        return item.sellIn --;
    }

    public void NormalItem(Item_Kero item){
        if (item.name.equals("Aged Brie")|| item.name.equals(concert)) {
            item.quality = QualityUp(item);
            logger.debug("UP: "+item.name+" "+item.sellIn+" "+item.quality);
        }
        else{
            item.quality = QualityDown(item);
            logger.debug("DW: "+item.name+" "+item.sellIn+" "+item.quality);
        }
    }

    public int QualityUp(Item_Kero item){
        if ((item.name.equals(concert))&&(item.sellIn < 0)) {
            return item.quality=0;
        } if (item.quality < 50) {
            item.quality=WhenQualityUnderFifty(item);
        } return item.quality;
    }

    public int QualityDown(Item_Kero item) {
        if (item.quality > 0) {
            item.quality--;
            if (item.sellIn < 0 && item.quality > 0) {
                item.quality--;
            } if (item.name.equals("Conjured Mana Cake")&& item.quality > 0){
                item.quality=ConjuredThings(item);
            }
        } return item.quality;
    }

    public int WhenQualityUnderFifty(Item_Kero item){
        item.quality ++;
        if (item.name.equals(concert)) {
            item.quality = DayConcert(item);
        } else if (item.sellIn < 0){
            item.quality ++;
        }
        return item.quality;
    }

    public int DayConcert(Item_Kero item){
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality ++;
        }
        if (item.sellIn < 6 && item.quality < 50) {
            item.quality ++;
        }
        return item.quality;
    }

    public int ConjuredThings(Item_Kero item){
        item.quality--;
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
        return item.quality;
    }

    public Item_Kero[] getItems() {
        return items;
    }
}