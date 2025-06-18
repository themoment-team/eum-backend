package com.themoment.board.controller;

import com.themoment.board.dto.GetItemDTO;
import com.themoment.board.dto.GetItemResponseDTO;
import com.themoment.board.dto.ImageLink.ImageLinkDTO;
import com.themoment.board.dto.ImageLink.ImageLinkResponseDTO;
import com.themoment.board.service.GetItemService;
import com.themoment.board.service.ImageLinkGenerator;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/getitem")
@RequiredArgsConstructor
public class GetItemController {

    private final ImageLinkGenerator imageLinkGenerator;
    private final GetItemService getItemService;

    @PostMapping("/makelink")
    public ResponseEntity<ImageLinkResponseDTO> linkGenerator(@RequestBody ImageLinkDTO imageLinkDTO) {
        ImageLinkResponseDTO response = imageLinkGenerator.imageLinkGenerate(imageLinkDTO.getFileType(), "getitem");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody(required = false) GetItemDTO GetItemdto) {
        getItemService.post(GetItemdto);
    }

    @GetMapping("/view/{boardid}")
    public ResponseEntity<GetItemResponseDTO> View(@PathVariable Long boardid) {
        GetItemResponseDTO response = getItemService.view(boardid);
        return ResponseEntity.ok(response);
    }
}