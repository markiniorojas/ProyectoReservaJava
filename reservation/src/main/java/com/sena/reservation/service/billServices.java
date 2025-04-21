// package com.sena.reservation.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Service;

// import com.sena.reservation.DTO.billDtos;
// import com.sena.reservation.DTO.responseDTO;
// import com.sena.reservation.interfaces.IBill;
// import com.sena.reservation.interfaces.IReservation;
// import com.sena.reservation.model.bill;
// import com.sena.reservation.model.billStatus;
// import com.sena.reservation.model.reservation;

// @Service
// public class billServices {

//     @Autowired
//     private IBill billData;

//     @Autowired
//     private IReservation reservationData; // Si necesitas verificar la reserva

//     // Obtener todas las facturas
//     public List<bill> findAllBills() {
//         return billData.findAll();
//     }

//     // Obtener una factura por ID
//     public Optional<bill> findByIdBill(int id) { // Asumiendo que el ID de Bill es Long
//         return billData.findById(id);
//     }

//     // Obtener facturas por estado de pago
//     public List<bill> getBillsByStatus(String status) {
//         return billData.findAllByBillStatus(status);
//     }

//     // Método para verificar si una reserva existe (si es necesario)
//     public boolean existsReservationById(int reservationId) {
//         return reservationData.existsById(reservationId);
//     }

//     // Método para guardar una factura
//     public boolean save(billDtos billDto) {
//         int reservationId = billDto.getId_reservation();

//         if (existsReservationById(reservationId)) {
//             bill newBill = convertDtoToBill(billDto);
//             if (newBill != null) {
//                 billData.save(newBill);
//                 return true;
//             }
//         } else {
//             throw new RuntimeException("La reserva con ID " + reservationId + " no existe.");
//         }
//         return false;
//     }

//     // Convertir DTO a entidad Bill
//     public bill convertDtoToBill(billDtos billDto) {
//         Optional<reservation> reservationEntity = reservationData.findById(billDto.getIdReservation());

//         if (reservationEntity.isPresent()) {
//             try {
//                 billStatus status = billStatus.valueOf(billDto.getBillStatus().toUpperCase());

//                 bill bill = new bill();
//                 bill.setReservation(reservationEntity.get());
//                 bill.setPromotionId(billDto.getId_promotion()); // Permitimos guardar el ID aunque la tabla no exista
//                 bill.setTotal_price(billDto.getTotal_price());
//                 bill.setBillStatus(status);
//                 return bill;
//             } catch (Exception e) {
//                 throw new RuntimeException("Error al convertir los datos: " + e.getMessage());
//             }
//         }
//         return null;
//     }

//     // Actualizar una factura
//     public void update(Long id, billDtos billDto) {
//         var existingBill = findByIdBill(id);
//         if (existingBill.isPresent()) {
//             var bill = existingBill.get();

//             Long reservationId = billDto.getId_reservation();
//             if (!existsReservationById(reservationId)) {
//                 throw new RuntimeException("La reserva con ID " + reservationId + " no existe.");
//             }
//             bill.setReservation(reservationData.findById(reservationId).get());

//             bill.setPromotionId(billDto.getId_promotion()); // Permitimos actualizar el ID aunque la tabla no exista

//             bill.setTotal_price(billDto.getTotal_price());
//             try {
//                 billStatus status = billStatus.valueOf(billDto.getBillStatus().toUpperCase());
//                 bill.setBillStatus(status);
//                 billData.save(bill);
//             } catch (Exception e) {
//                 throw new RuntimeException("Error al actualizar la factura: " + e.getMessage());
//             }
//         } else {
//             throw new RuntimeException("La factura con ID " + id + " no existe.");
//         }
//     }

//     // Eliminar una factura
//     public responseDTO delete(Long id) {
//         var bill = findByIdBill(id);
//         responseDTO response = new responseDTO();
//         if (bill.isPresent()) {
//             billData.delete(bill.get());
//             response.setStatus(HttpStatus.OK);
//             response.setMessage("Factura eliminada con éxito");
//             return response;
//         } else {
//             response.setStatus(HttpStatus.NOT_FOUND);
//             response.setMessage("Factura no encontrada");
//             return response;
//         }
//     }

//     // Restaurar una factura (si tienes lógica para esto)
//     public responseDTO restore(Long id) {
//         var bill = billData.findById(id);
//         responseDTO response = new responseDTO();

//         if (bill.isPresent()) {
//             response.setStatus(HttpStatus.OK);
//             response.setMessage("Factura restaurada con éxito (si la lógica existe)");
//         } else {
//             response.setStatus(HttpStatus.NOT_FOUND);
//             response.setMessage("Factura no encontrada");
//         }
//         return response;
//     }
// }