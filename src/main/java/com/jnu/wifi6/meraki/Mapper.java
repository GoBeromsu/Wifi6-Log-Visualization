package com.jnu.wifi6.meraki;

import com.jnu.wifi6.meraki.DTO.ClientDeviceDTO;
import com.jnu.wifi6.meraki.DTO.GetMerakiDTO;
import com.jnu.wifi6.meraki.DTO.GetUsageDTO;
import java.util.List;

public class Mapper {


  public static GetMerakiDTO mapToDTO(List<ClientDeviceDTO> clients) {
    long totalClients = clients.size();
    long totalUsage = clients.stream().map(ClientDeviceDTO::usage).mapToLong(GetUsageDTO::total)
        .sum();
    return new GetMerakiDTO(totalClients, totalUsage);
  }


}
