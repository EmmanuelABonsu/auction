package com.tally.assignment.dataAccessObject;

import com.tally.assignment.modal.Bidder;

import java.util.TreeSet;

/**
    This Interface defines methods used to retrieve information from database. In this project, I use an in memory
    Data Structure (HashMap) as my database.
 */
public interface Dao {

    /**
     * Insert the bidder into the database using the bidders auction item as the key
     * @param bidder
     */
    void bidOnAuctionItem(Bidder bidder);

    /**
     * Retrieves the list of all bidders on the specified key(Auction item)
     * @param auctionItem lookup key in the database
     * @return TreeSet<Bidder>
     */
    TreeSet<Bidder> getHighestBidderByItem(String auctionItem);

    /**
     * Removes all data from the database
     */
    void clearAuctionSystem();
}
