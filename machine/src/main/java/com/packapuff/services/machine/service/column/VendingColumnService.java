package com.packapuff.services.machine.service.column;

import com.packapuff.services.machine.service.dto.column.*;

import java.util.List;

public interface VendingColumnService {

    List<VendingColumnResponse> createVendingColumns(List<CreateVendingColumnRequest> createVendingColumnRequest);

    List<VendingColumnResponse> getVendingColumns(GetVendingColumnsRequest getVendingColumnsRequest);

    VendingColumnResponse getVendingColumnByMdbCode(Integer machineId, String mdbCode);

    List<VendingColumnResponse> listVendingColumns(ListVendingColumnsRequest listVendingColumnsRequest);

    List<VendingColumnResponse> updateVendingColumns(List<UpdateVendingColumnRequest> updateVendingColumnRequestList);

    List<VendingColumnResponse> deleteVendingColumns(Integer machineId);
    VendingColumnResponse deleteVendingColumn(Integer machineId, String mdbCode);

}
