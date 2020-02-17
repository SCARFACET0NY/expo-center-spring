package com.anton.expocenterspring.services.impl;

import com.anton.expocenterspring.model.Exposition;
import com.anton.expocenterspring.model.Payment;
import com.anton.expocenterspring.model.Ticket;
import com.anton.expocenterspring.model.User;
import com.anton.expocenterspring.repositories.ExpositionRepository;
import com.anton.expocenterspring.repositories.PaymentRepository;
import com.anton.expocenterspring.repositories.TicketRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    private static final Long ID = 1L;
    private static final Integer PAGE_NUMBER = 0;
    private static final double PRICE1 = 50.0;
    private static final double PRICE2 = 15.15;
    private static final int QUANTITY1 = 2;
    private static final int QUANTITY2 = 1;
    private static final double TOTAL = 115.15;
    private static final Map<String, Ticket> cart = new HashMap<>();
    @Mock
    ExpositionRepository expositionRepository;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    PaymentRepository paymentRepository;
    @InjectMocks
    PaymentServiceImpl paymentService;
    User user;

    @BeforeAll
    static void before() {
        Exposition exposition1 = new Exposition();
        Exposition exposition2 = new Exposition();
        exposition1.setPrice(PRICE1);
        exposition2.setPrice(PRICE2);

        Ticket ticket1 = Ticket.builder().exposition(exposition1).quantity(QUANTITY1).build();
        Ticket ticket2 = Ticket.builder().exposition(exposition2).quantity(QUANTITY2).build();

        cart.put("1", ticket1);
        cart.put("2", ticket2);
    }

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(ID);
    }

    @Test
    void createTicketTest() throws Exception {
        Exposition exposition = new Exposition();
        exposition.setId(ID);
        exposition.setStartDate(LocalDate.now());

        when(expositionRepository.findById(anyLong())).thenReturn(Optional.of(exposition));

        Ticket ticket = paymentService.createTicket(ID);

        assertNotNull(ticket);
        assertEquals(exposition, ticket.getExposition());
        assertEquals(exposition.getStartDate(), ticket.getDate());
        assertEquals(PaymentServiceImpl.ONE_TICKET, ticket.getQuantity());

        verify(expositionRepository).findById(anyLong());
    }

    @Test
    void createPaymentTest() throws Exception {
        Payment payment = paymentService.createPayment(TOTAL, user);

        assertNotNull(payment);
        assertEquals(TOTAL, payment.getTotal());
        assertEquals(user.getId(), payment.getUser().getId());
    }

    @Test
    void getCartTotalTest() throws Exception {
        assertEquals(TOTAL, paymentService.getCartTotal(cart));
    }

    @Test
    void savePaymentTest() throws Exception {
        Payment payment = new Payment();
        payment.setTotal(TOTAL);
        payment.setUser(user);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment returnedPayment = paymentService.savePayment(TOTAL, user, cart);

        assertNotNull(returnedPayment);
        assertEquals(TOTAL, returnedPayment.getTotal());
        assertEquals(ID, returnedPayment.getUser().getId());

        verify(paymentRepository).save(any(Payment.class));
        verify(ticketRepository, times(cart.size())).save(any(Ticket.class));
    }

    @Test
    void getPaymentsPageForUserTest() {
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setTotal(TOTAL);
        payments.add(payment);

        PageImpl paymentsPage = new PageImpl<>(payments);

        when(paymentRepository.findAllByUserOrderByDateDesc(any(User.class), any(PageRequest.class)))
                .thenReturn(paymentsPage);

        Page<Payment> returnedPaymentPage = paymentService.getPaymentsPageForUser(user, PAGE_NUMBER);

        assertNotNull(returnedPaymentPage);
        assertEquals(TOTAL, ((Payment) (returnedPaymentPage.get().toArray()[0])).getTotal());

        verify(paymentRepository).findAllByUserOrderByDateDesc(any(User.class), any(PageRequest.class));
    }
}