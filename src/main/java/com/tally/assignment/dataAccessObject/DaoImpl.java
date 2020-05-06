package com.tally.assignment.dataAccessObject;

import com.tally.assignment.modal.Bidder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DaoImpl implements Dao {
    private final Map<String, TreeSet<Bidder>> dataBase;

    public DaoImpl(Map<String, TreeSet<Bidder>> dataBase) {
        this.dataBase = new HashMap<>(); //Could use Concurrent HashMap to take advantage of full Concurrency
    }

    @Override
    public void bidOnAuctionItem(Bidder bidder) {
        dataBase.putIfAbsent(bidder.getAuctionItem(), new TreeSet<>());
        dataBase.get(bidder.getAuctionItem()).add(bidder);
    }

    @Override
    public TreeSet<Bidder> getHighestBidderByItem(String auctionItem) {
        return dataBase.get(auctionItem);
    }

    @Override
    public void clearAuctionSystem() {
        dataBase.clear();
    }
}
