import {PaymentMethod} from "./enum/payment.method";

export interface Payment {
  transactionId: String;
  amount: number;
  paymentDate: Date;
  orderId: number;
  countryOrigin: string;
  countryDestination: string;
  method: PaymentMethod;
  reason: string;
}
