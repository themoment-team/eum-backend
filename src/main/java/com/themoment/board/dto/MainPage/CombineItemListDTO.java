package com.themoment.board.dto.MainPage;

import lombok.Data;

@Data
public class CombineItemListDTO {
    private ItemListDTO<GetItemListDTO> getitem;
    private ItemListDTO<LostItemListDTO> lostitem;

    public CombineItemListDTO(ItemListDTO<GetItemListDTO> getitem, ItemListDTO<LostItemListDTO> lostitem) {
        this.getitem = getitem;
        this.lostitem = lostitem;
    }
}
