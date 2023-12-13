package com.jnu.wifi6.meraki;

import com.jnu.wifi6.meraki.DTO.ClientDeviceDTO;
import com.jnu.wifi6.meraki.DTO.GetMerakiDTO;
import com.jnu.wifi6.meraki.DTO.GetUsageDTO;
import java.util.List;
import reactor.core.publisher.Mono;

public class Mapper {


  public static Mono<GetMerakiDTO> mapToDTO(Mono<List<ClientDeviceDTO>> clientDeviceDTO) {
    return clientDeviceDTO.map(Mapper::caculateTotals);
  }

  private static GetMerakiDTO caculateTotals(List<ClientDeviceDTO> clients) {
    long totalClients = clients.size();
    long totalUsage = clients.stream().map(ClientDeviceDTO::usage).mapToLong(GetUsageDTO::total)
        .sum();
    return new GetMerakiDTO(totalClients, totalUsage);
  }

}
