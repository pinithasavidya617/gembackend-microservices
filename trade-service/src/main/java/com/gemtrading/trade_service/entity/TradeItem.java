//package com.gemtrading.trade_service.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "trade_items")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class TradeItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "trade_id", nullable = false)
//    private Trade trade;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "gem_id" , nullable = false)
//    private GemStone stone;
//
//    @Column(nullable = false)
//    private Integer quantity;
//
//    @Column(nullable = false)
//    private BigDecimal unitPrice;
//}