package com.example.purchase;

import com.example.buyer.Buyer;
import com.example.buyer.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final BuyerRepository buyerRepository;
    //구매결정?
    public Purchase findByproductId(Integer productId) {
        Purchase purchase = purchaseRepository.findByProductId(productId);
        return purchase;
    }

    //구매하기
    @Transactional
    public void savePurchase(Integer buyerId, PurchaseRequest.SaveDTO reqDTO){
        Buyer buyer = buyerRepository.findByBuyerId(buyerId);
        purchaseRepository.save(buyerId, buyer.getBuyerName(), reqDTO);
        purchaseRepository.updateQty(reqDTO);
    }

    //구매수량 수정하기
    public void changePurQty(Integer buyerId, PurchaseRequest.UpdateDTO reqDTO) {
        purchaseRepository.updateByBuyerId(buyerId,reqDTO);
    }

    //내 구매목록 보기
    public List<Purchase> getPurchaseList(Integer buyerId) {
        List<Purchase> purchaseList = purchaseRepository.findByBuyerId(buyerId);
        return purchaseList;
    }
}
