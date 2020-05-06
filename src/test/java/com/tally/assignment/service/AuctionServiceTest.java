package com.tally.assignment.service;

import com.tally.assignment.dataAccessObject.Dao;
import com.tally.assignment.dataAccessObject.DaoImpl;
import com.tally.assignment.modal.Bidder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class AuctionServiceTest {
    @Autowired
    public static AuctionService auctionService;

    @Autowired
    public static  Dao dao;

    @BeforeAll
    // Resource Initializer for the test Methods
    public static void setUp() {
        Map<String, TreeSet<Bidder>> testDataBase = new HashMap<>();
        dao = new DaoImpl(testDataBase);
        auctionService = new AuctionService(dao);
    }

    @Test
    public void testSingleBidderReturnBidderWithMinimumStartingBid(){
        String skate = "Skate";
        Bidder bidder = new Bidder("James", 10.00, 15.00, skate, 2.00);
        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(skate));
        auctionService.bidOnAuctionItem(bidder);
        assertEquals(bidder.toString(), auctionService.getHighestBidderByItem(skate));
        dao.clearAuctionSystem();

    }

    @Test
    public void testTwoBiddersWithTheSameBidReturnFirstBidder(){
        String shirt = "Shirt";
        Bidder james = new Bidder("James", 10.00, 10.00, shirt, 2.00);
        Bidder fred = new Bidder("Fred", 10.00, 10.00, shirt, 2.00);
        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(shirt));
        auctionService.bidOnAuctionItem(fred);
        auctionService.bidOnAuctionItem(james);
        assertEquals("{Bidder: Fred, Bid Amount: 10.0}", auctionService.getHighestBidderByItem(shirt));
        dao.clearAuctionSystem();
    }

    @Test
    public void testBiddingOnDifferentItemsReturnsTheHighestBidderForEachItem(){
        String eraser = "Eraser";
        String pencil = "Pencil";

        Bidder john = new Bidder("John", 10.00, 15.00, pencil, 1.00);
        Bidder matt = new Bidder("Matt", 12.00, 14.00, pencil, 2.00);

        Bidder james = new Bidder("James", 5.00, 9.00, eraser, 1.00);
        Bidder fred = new Bidder("Fred", 1.00, 14.00, eraser, 5.00);

        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(pencil));
        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(eraser));
        auctionService.bidOnAuctionItem(john);
        auctionService.bidOnAuctionItem(matt);
        auctionService.bidOnAuctionItem(james);
        auctionService.bidOnAuctionItem(fred);
        assertEquals("{Bidder: John, Bid Amount: 15.0}", auctionService.getHighestBidderByItem("Pencil"));
        assertEquals("{Bidder: Fred, Bid Amount: 11.0}", auctionService.getHighestBidderByItem("Eraser"));
        dao.clearAuctionSystem();

    }

    @Test
    public void testBiddingOnSkatesReturnsMason() {
        String skate = "Skate";

        Bidder alicia = new Bidder("Alicia", 50.00, 80.00, skate, 3.00);
        Bidder olivia = new Bidder("Olivia", 60.00, 82.00, skate, 2.00);
        Bidder mason = new Bidder("Mason", 55.00, 85.00, skate, 5.00);

        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(skate));
        auctionService.bidOnAuctionItem(alicia);
        auctionService.bidOnAuctionItem(olivia);
        auctionService.bidOnAuctionItem(mason);
        assertEquals("{Bidder: Mason, Bid Amount: 85.0}", auctionService.getHighestBidderByItem(skate));
        dao.clearAuctionSystem();
    }

    @Test
    public void testBiddingOnUnicycleReturnsAlicia() {
        String unicycle = "Unicycle";

        Bidder alicia = new Bidder("Alicia", 700.00, 725.00, unicycle, 2.00);
        Bidder olivia = new Bidder("Olivia", 599.00, 725.00, unicycle, 15.00);
        Bidder mason = new Bidder("Mason", 625.00, 725.00, unicycle, 8.00);

        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(unicycle));
        auctionService.bidOnAuctionItem(alicia);
        auctionService.bidOnAuctionItem(olivia);
        auctionService.bidOnAuctionItem(mason);
        assertEquals("{Bidder: Alicia, Bid Amount: 722.0}", auctionService.getHighestBidderByItem(unicycle));
        dao.clearAuctionSystem();
    }

    @Test
    public void testBiddingOnHoverBoardReturnsOlivia() {
        String hoverboard = "Hover Board";

        Bidder alicia = new Bidder("Alicia", 2500, 3000, hoverboard, 500);
        Bidder olivia = new Bidder("Olivia", 2800, 3100, hoverboard, 201);
        Bidder mason = new Bidder("Mason", 2501, 3200, hoverboard, 247);

        assertThrows(NullPointerException.class, () -> auctionService.getHighestBidderByItem(hoverboard));
        auctionService.bidOnAuctionItem(alicia);
        auctionService.bidOnAuctionItem(olivia);
        auctionService.bidOnAuctionItem(mason);
        assertEquals("{Bidder: Olivia, Bid Amount: 3001.0}", auctionService.getHighestBidderByItem(hoverboard));
        dao.clearAuctionSystem();
    }
}
