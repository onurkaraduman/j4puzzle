package com.onrkrdmn.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Surface types of cube
 *
 * @author Onur Karaduman
 * @since 09.04.17
 */
public enum SurfaceType {
    LEFT(0),
    TOP(1),
    RIGHT(2),
    FRONT(3),
    BOTTOM(4),
    BACK(5);

    private int id;

    private SurfaceType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get surface type from enum with given id
     *
     * @param count
     * @return
     */
    public static SurfaceType getSurfaceType(int count) {
        List<SurfaceType> collect = Arrays.stream(SurfaceType.values()).filter(t -> t.id == count)
                .collect(Collectors.toList());
        if (collect != null && collect.size() > 0) {
            return collect.get(0);
        }
        return null;
    }

    /**
     * Get next surface type with given type
     * Flow:
     * Left-Top-Right-Front-Bottom-Back
     *
     * @param type
     * @return
     */
    public static SurfaceType getNext(SurfaceType type) {
        int typeId = type.getId();
        return getSurfaceType(++typeId);
    }
}
