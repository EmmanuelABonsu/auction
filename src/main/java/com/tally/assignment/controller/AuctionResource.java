package com.tally.assignment.controller;

import com.tally.assignment.modal.Bidder;
import com.tally.assignment.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
/**
 * This class defines the RESTful API templates
 */
public class AuctionResource {
    private final AuctionService auctionService;

    @Autowired
    public AuctionResource(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    /**
     * Gets the highest bidder to the client
     * @param item Auction item specified by client
     * @return String of highest bidders's name and bidding amount
     */
    @GetMapping(value = "api/v1/getHighestBidder/{auctionItem}")
    public String getHighestBidderByItem(@PathVariable("auctionItem") String item) {
       return auctionService.getHighestBidderByItem(item);
    }

    /**
     * Posts the bidder into the Auction Database
     * @param bid Bidder Object
     */
    @PostMapping(value = "api/v1/register/bid")
    @ResponseBody
    public String bidOnAuction(@RequestBody Bidder bid) {
        auctionService.bidOnAuctionItem(bid);
        return bid.getBidderName() + " bid on " + bid.getAuctionItem();
    }
}
