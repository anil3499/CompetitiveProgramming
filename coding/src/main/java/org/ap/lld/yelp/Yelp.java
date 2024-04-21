package org.ap.lld.yelp;

public class Yelp {
    /*
    * 100Mi DAU
    * 200 Mi Bussiness
    * 5 query per day
    *
    * 100Mil DAU * 5 queries each DAU / 100,000 seconds in a day = 5000 query per secs
    *
    * Storage to hold 200Mil bussiness
    *
    * 100 Mil DAU
    * 200 Business
    *
    * Bussiness - id,add,city,state,country,lattitude, longitude
    * 200Mill * 1KB = 200GB
    *
    * nearbysearch table -- location , bussinessid
    *
    * 200Mil * 24 bytes = 5GB
    *
    *
    *Services are read heavy, read QPS are heavy than write QPS
    * writes are not immideate
    *consistency is not a problem
    *
    *
    * Question
    *
    * can we map 2 dimentional data into ione dimention so we can build single index on it.
    * ans - yes
    * 2 approaches-
    * 1. hash based [Even Grid /GeoHAsh/Cartesian Tiers]
    * 2. Tree bases [QuadTree/Google S2/ RTree
    * */

    /**
     * User Class:
     *
     * Attributes:
     * UserID
     * Username
     * Password
     * Email
     * ...
     * Business Class:
     *
     * Attributes:
     * BusinessID
     * Name
     * Address
     * Category
     * ...
     * Review Class:
     *
     * Attributes:
     * ReviewID
     * UserID (Foreign Key)
     * BusinessID (Foreign Key)
     * Rating
     * Comment
     * ...
     * Administrator Class:
     *
     * Attributes:
     * AdminID
     * Username
     * Password
     * ...
     * BusinessOwner Class:
     *
     * Attributes:
     * OwnerID
     * BusinessID (Foreign Key)
     * UserID (Foreign Key)
     * ...
     * */
}
