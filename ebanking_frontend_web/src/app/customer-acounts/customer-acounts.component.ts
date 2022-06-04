import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";

@Component({
  selector: 'app-customer-acounts',
  templateUrl: './customer-acounts.component.html',
  styleUrls: ['./customer-acounts.component.css']
})
export class CustomerAcountsComponent implements OnInit {
  customerId!: string;
  customer!:Customer;
  constructor(private route: ActivatedRoute,private router:Router) {
    this.customer=this.router.getCurrentNavigation()?.extras.state as Customer;
  }

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id']
  }

}
