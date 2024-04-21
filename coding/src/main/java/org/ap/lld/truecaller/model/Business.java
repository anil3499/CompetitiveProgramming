package org.ap.lld.truecaller.model;


import lombok.Getter;
import lombok.Setter;
import org.ap.lld.truecaller.model.common.Contact;
import org.ap.lld.truecaller.model.common.PersonalInfo;
import org.ap.lld.truecaller.model.common.SocialInfo;
import org.ap.lld.truecaller.model.common.Tag;

import java.util.Map;

@Getter
@Setter
public class Business {
    private String businessName;
    private String businessDescription;
    private Tag tag;
    private BusinessSize businessSize;
    private Map<Days, OperatingHours> openHours;
    private Contact contact;
    private PersonalInfo personalInfo;
    private SocialInfo socialInfo;

    public Business(String name, Tag tag) {
        this.businessName = name;
        this.tag = tag;
    }
}
