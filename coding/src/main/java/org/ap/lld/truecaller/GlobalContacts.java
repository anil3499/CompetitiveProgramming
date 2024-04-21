package org.ap.lld.truecaller;

import lombok.Getter;
import org.ap.lld.truecaller.model.tries.ContactTrie;

public class GlobalContacts {
    private GlobalContacts() {
    }
    public static GlobalContacts INSTANCE = new GlobalContacts();
    @Getter
    private ContactTrie contactTrie = new ContactTrie();
}
