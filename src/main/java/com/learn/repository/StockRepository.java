package com.learn.repository;

import com.learn.domain.StockDomain;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockDomain, Integer> {

    @Query(value = "select ms.* from mst_stock ms where lower(ms.nama_barang)=:namaBarang order by ms.stock_id asc limit 1",nativeQuery = true)
    StockDomain findByNameBarang(@Param("namaBarang") String namaBarang);

    @Query(value = "select ms.* from mst_stock ms where ms.stock_id=:stockId order by ms.stock_id asc limit 1",nativeQuery = true)
    StockDomain findByStockId(@Param("stockId") Integer namaBarang);
}
