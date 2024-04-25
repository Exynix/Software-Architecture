package com.arquitectura.taller2_jakarta_emuladorpasarelapagos;


import com.arquitectura.taller2_jakarta_emuladorpasarelapagos.model.PaymentData;

import com.arquitectura.taller2_jakarta_emuladorpasarelapagos.model.PaymentResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/payments")
public class PaymentResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transactionResponse(PaymentData paymentData) {

        boolean validTransaction = validateTransaction(paymentData);

        if (validTransaction) {
            return Response.ok(new PaymentResponse("Transaction approved", true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new PaymentResponse("Transaction failed", false))
                    .build();
        }
    }

    public boolean validateTransaction(PaymentData paymentData) {
        // Basic, mock validations of payment data

        return true; // placeholder return.
    }

}
