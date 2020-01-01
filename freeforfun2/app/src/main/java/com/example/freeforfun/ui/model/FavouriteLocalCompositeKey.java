package com.example.freeforfun.ui.model;


import javax.persistence.Column;

import java.io.Serializable;

/**
 * composite key for join columns id_user,id_local from table
 * m:m "favouritelocals"
 */

public class FavouriteLocalCompositeKey implements Serializable {

    private Long localId;

    private Long userId;


    public FavouriteLocalCompositeKey(Long localId, Long userId) {
        this.localId = localId;
        this.userId = userId;
    }

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
