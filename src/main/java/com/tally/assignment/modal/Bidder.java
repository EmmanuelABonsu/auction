package com.tally.assignment.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * This class serves as the custom blob used to specify the bidders' specifications and details
 * including auction Item, bidder's Name, starting bid amount, maximum bid and incremental amounts.
 */
public class Bidder implements Comparable<Bidder> {

    private  final String auctionItem;

    private final String bidderName;

    public double startingBid;

    private final double maxBid;

    private final double increment;

    //Constructor is specified with JsonProperty Annotation in order to allow Json Object input from clients
    public Bidder(@JsonProperty("bidder") String bidderName, @JsonProperty("startAmount") double startingBid,
                  @JsonProperty("maxAmount") double maxBid, @JsonProperty("auctionItem") String auctionItem,
                  @JsonProperty("incrementalAmount") double increment) {
        this.bidderName = bidderName;
        this.startingBid = startingBid;
        this.maxBid = maxBid;
        this.auctionItem = auctionItem;
        this.increment = increment;
    }

    public String getBidderName() {
        return bidderName;
    }

    public double getStartingBid() {
        return startingBid;
    }

    public double getMaxBid() {
        return maxBid;
    }

    public String getAuctionItem() {
        return auctionItem;
    }

    public double getIncrement() {
        return increment;
    }

    public void setStartingBid(double startingBid) {
        this.startingBid = startingBid;
    }

    @Override
    public String toString() {
        return String.format("{Bidder: %s, Bid Amount: %s}", bidderName, startingBid); //Custom toString() to display winning bidder
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidderName, startingBid, maxBid);
    }

    @Override
    public int compareTo(Bidder o) {
        if (this.startingBid != o.getStartingBid()) {
            return Double.compare(this.startingBid, o.getStartingBid());
        } else {
            return -1; //Using -1 per problem specifications. i.e if two bidders have the same bidding amount, preference is given to the first bidder

        }
    }
}
