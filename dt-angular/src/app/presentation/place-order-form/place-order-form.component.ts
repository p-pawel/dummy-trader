import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PlaceOrderRequest} from "../../model/placeOrderRequest";

@Component({
  selector: 'app-place-order-form',
  templateUrl: './place-order-form.component.html',
  styleUrls: ['./place-order-form.component.scss']
})
export class PlaceOrderFormComponent implements OnInit {

  @Input()
  set price(value: number) {
    this.placeOrderForm.get('price')?.setValue(value);
    this.calcTotal();
  }

  @Input()
  set balance(value: number) {
    this.placeOrderForm.get('balance')?.setValue(value);
    this.calcTotal();
  }

  @Input()
  pendingRequest: boolean = false;

  @Output()
  placeOrder: EventEmitter<PlaceOrderRequest> = new EventEmitter<PlaceOrderRequest>();

  placeOrderForm: FormGroup;

  constructor(
    private fb: FormBuilder,
  ) {
    this.placeOrderForm = this.createNewForm();
  }

  ngOnInit(): void {
  }

  private createNewForm(): FormGroup {
    return this.fb.group({
      price: [{value: '', disabled: true}, Validators.required],
      amount: ['0.21000000', Validators.required],
      total: [{value: '', disabled: true}, Validators.required],
      balance: ['', Validators.required],
      enough: ['', Validators.required],
    });
  }

  private calcTotal() {
    const price = this.placeOrderForm.get('price')?.value;
    const amount = this.placeOrderForm.get('amount')?.value;
    const balance = this.placeOrderForm.get('balance')?.value;
    const total = this.roundToTwo(price * amount);
    this.placeOrderForm.get('total')?.setValue(total);
    this.placeOrderForm.get('enough')?.setValue(total <= balance && total > 0);
  }

  private roundToTwo(num: number) {
    return +(Math.round(Number.parseFloat(num + "e+2")) + "e-2");
  }

  onSubmit() {
    let placeOrderRequest: PlaceOrderRequest = {
      amount: this.placeOrderForm.get('amount')?.value
    };
    this.placeOrder.emit(placeOrderRequest);
  }
}
