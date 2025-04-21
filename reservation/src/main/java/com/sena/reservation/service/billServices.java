package com.sena.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.reservation.DTO.billDtos;
import com.sena.reservation.DTO.responseDTO;
import com.sena.reservation.interfaces.IBill;
import com.sena.reservation.interfaces.IReservation;
import com.sena.reservation.model.bill;
import com.sena.reservation.model.billStatus;
import com.sena.reservation.model.reservation;

@Service
public class billServices {

    @Autowired
    private IBill billData;

    @Autowired
    private IReservation reservationData;

    public List<bill> findAll() {
        return billData.findAll();
    }

    public Optional<bill> findById(int id) {
        return billData.findById(id);
    }

    public void save(billDtos billDto) {
        billData.save(convertDtoToEntity(billDto));
    }

    public void update(billDtos billDto) {
        var existing = findById(billDto.getId_bill());
        if (existing.isPresent()) {
            bill bill = existing.get();
            Optional<reservation> reservation = reservationData.findById(billDto.getId_reservation());
            reservation.ifPresent(bill::setReservation);
            bill.setPromotionId(billDto.getId_promotion());
            bill.setTotal_price(billDto.getTotal_price());
            bill.setBillStatus(billStatus.valueOf(billDto.getBillStatus().toUpperCase()));
            billData.save(bill);
        }
    }

    public responseDTO delete(int id) {
        var bill = findById(id);
        responseDTO response = new responseDTO();
        if (bill.isPresent()) {
            billData.delete(bill.get());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Factura eliminada correctamente");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("La factura no existe");
        }
        return response;
    }

    private bill convertDtoToEntity(billDtos dto) {
        reservation reservation = reservationData.findById(dto.getId_reservation()).orElseThrow(() ->
            new RuntimeException("Reserva no encontrada")
        );
        return new bill(
            dto.getId_bill(),
            reservation,
            dto.getId_promotion(),
            dto.getTotal_price(),
            billStatus.valueOf(dto.getBillStatus().toUpperCase())
        );
    }
}
