package com.jubox70.springai.domain.model;

import java.util.List;

public final class DefaultKnowledgeEntries {

    private DefaultKnowledgeEntries() {
    }

    public static final List<KnowledgeEntry> RESERVATION_POLICIES = List.of(
            new KnowledgeEntry("reservation-policy-1", "Reservation policy 1 - You can cancel the reservation 15 days before the check-in date to get a full refund."),
            new KnowledgeEntry("reservation-policy-2", "Reservation policy 2 - If you cancel the reservation 7 days before the check-in date, you will get a 50% refund."),
            new KnowledgeEntry("reservation-policy-3", "Reservation policy 3 - If you cancel the reservation less than 7 days before the check-in date, you will not get any refund."),
            new KnowledgeEntry("reservation-policy-4", "Reservation policy 4 - If your name is Joseba, you will have a penalty of 50% of the reservation price."),
            new KnowledgeEntry("reservation-policy-5", "Reservation policy 5 - If your name is Angela, you will get all the funds, a whisky and a cigar if you cancel the reservation.")
    );
}

