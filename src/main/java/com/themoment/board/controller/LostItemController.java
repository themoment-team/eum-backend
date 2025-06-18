package com.themoment.board.controller;

import com.themoment.board.dto.ImageLink.ImageLinkDTO;
import com.themoment.board.dto.ImageLink.ImageLinkResponseDTO;
import com.themoment.board.dto.LostItemDTO;
import com.themoment.board.dto.LostItemResponseDTO;
import com.themoment.board.service.ImageLinkGenerator;
import com.themoment.board.service.LostItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lostitem")
@RequiredArgsConstructor
public class LostItemController {
    private final ImageLinkGenerator imageLinkGenerator;
    private final LostItemService lostItemService;

    @PostMapping("/makelink")
    public ResponseEntity<ImageLinkResponseDTO> linkGenerator(@RequestBody ImageLinkDTO imageLinkDTO) {
        ImageLinkResponseDTO response = imageLinkGenerator.imageLinkGenerate(imageLinkDTO.getFileType(), "lostitem");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody(required = false) LostItemDTO lostItemDTO) {
        lostItemService.post(lostItemDTO);
    }


    @GetMapping("/view/{boardid}")
    public ResponseEntity<LostItemResponseDTO> View(@PathVariable Long boardid) {
        LostItemResponseDTO response = lostItemService.view(boardid);
        return ResponseEntity.ok(response);
    }
}