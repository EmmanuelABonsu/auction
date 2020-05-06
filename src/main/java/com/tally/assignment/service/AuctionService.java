package com.tally.assignment.service;

import com.tally.assignment.dataAccessObject.Dao;
import com.tally.assignment.modal.Bidder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
/**
 * This class implements the functional specifications/ Business Logic and the Algorithm for
 * determine the winning bidder for each auction item
 */
public class AuctionService {
    private final Dao dao;

    @Autowired
    public AuctionService(Dao dao) {
        this.dao = dao;
    }

    /**
     * Inserts Bidder into database by using the bidder's details
     * @param bidder
     */
    public void bidOnAuctionItem(Bidder bidder) {
        dao.bidOnAuctionItem(bidder);
    }

    /**
     * Computes the winning bidder for all the bidders that bid on specified auction Item
     * @param auctionItem Auction item
     * @return String of the Name ob bidder and Bidding Amount
     */
    public String getHighestBidderByItem(String auctionItem) {
        TreeSet<Bidder> allBidders =  dao.getHighestBidderByItem(auctionItem);
        if (allBidders.size() == 1) { // this prevents the bidder from bidding against him/herself
            return allBidders.first().toString();
        }

        while (allBidders.first().getStartingBid() < allBidders.first().getMaxBid() && allBidders.size() >= 2) {
            Bidder lossingBidder = allBidders.pollFirst();
            lossingBidder.setStartingBid(lossingBidder.getStartingBid() + lossingBidder.getIncrement());
            if (lossingBidder.getStartingBid() <= lossingBidder.getMaxBid()) {
                allBidders.add(lossingBidder);
            }
            if(allBidders.first().getMaxBid() == allBidders.first().getStartingBid()) {
                allBidders.pollFirst();
            }
        }
      return allBidders.last().toString();
    }
}
