package com.example.freeforfun.ui.model;

public class FavouriteLocals {
    private FavouriteLocalCompositeKey id;
    private EVoteType vote;

    public FavouriteLocals(FavouriteLocalCompositeKey id, EVoteType voteType) {
        this.id = id;
        this.vote = voteType;
    }


    public FavouriteLocalCompositeKey getId() {
        return id;
    }

    public void setId(FavouriteLocalCompositeKey id) {
        this.id = id;
    }
    public EVoteType getVoteType() {
        return vote;
    }

    public void setVoteType(EVoteType voteType) {
        this.vote = voteType;
    }
}
