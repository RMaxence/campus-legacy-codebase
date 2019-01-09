package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void foo() {
        Item_Kero[] items = new Item_Kero[] { new Item_Kero("foo", 0, 0) };
        GildedRose_Kero app = new GildedRose_Kero(items);
        app.updateQuality();
        assertThat(app.items[0].name).isEqualTo("foo");
    }

    @Test
    void cheese() {
        Item_Kero[] items = new Item_Kero[] { new Item_Kero("Aged Brie", 2, 0) };
        GildedRose_Kero app = new GildedRose_Kero(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(1);
    }

    @Test
    void concert() {
        Item_Kero[] items = new Item_Kero[] { new Item_Kero("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose_Kero app = new GildedRose_Kero(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isNotZero();
    }

    @Test
    void D4RK() {
        Item_Kero[] items = new Item_Kero[] { new Item_Kero("Conjured Mana Cake", 3, 25) };
        GildedRose_Kero app = new GildedRose_Kero(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(22);
        assertThat(app.items[0].sellIn).isEqualTo(2);
    }

}
