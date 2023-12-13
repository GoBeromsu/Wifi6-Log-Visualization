package com.jnu.wifi6.meraki.DTO;

import java.time.ZonedDateTime;

public record ClientDeviceDTO(
    String id,
    String mac,
    String description,
    String ip,
    String ip6,
    String ip6Local,
    String user,
    ZonedDateTime firstSeen,
    ZonedDateTime lastSeen,
    String manufacturer,
    String os,
    String deviceTypePrediction,
    String recentDeviceSerial,
    String recentDeviceName,
    String recentDeviceMac,
    String recentDeviceConnection,
    String ssid,
    String vlan,
    String switchport,
    GetUsageDTO usage,
    String status,
    String notes,
    String groupPolicy8021x,
    String adaptivePolicyGroup,
    Boolean smInstalled,
    String namedVlan,
    String pskGroup
) {

}
