import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountDetails} from "../model/account.model";
import {catchError, Observable, throwError} from "rxjs";
import {AccountsService} from "../services/accounts.service";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize = 5;
  accountObservable!: Observable<AccountDetails>;
  operationsFormGroup!: FormGroup;
  error!:string;

  constructor(private fb: FormBuilder, private accountService: AccountsService) {
  }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control('')
    });
    this.operationsFormGroup=this.fb.group({
      operationType:this.fb.control(null),
      amount:this.fb.control(0),
      declarations: this.fb.control(null),
      accountDestination: this.fb.control(null)
    });
  }

  handleSearchAccounts() {
    let accountId: string = this.accountFormGroup.value.accountId;
    this.accountObservable=this.accountService.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError((err) => {
        this.error = err.message;
        return throwError(err);
      })
    );
  }

  goToPage(page:number) {
    this.currentPage=page;
    this.handleSearchAccounts();

  }

  handleAccountOperation() {
    let accountId:string=this.accountFormGroup.value.accountId;
    let operationType=this.operationsFormGroup.value.operationType;
    let amount : number=this.operationsFormGroup.value.amount;
    let description : string=this.operationsFormGroup.value.description;
    let accountDestination : string=this.operationsFormGroup.value.accountDestination;
    if(operationType=='DEBIT'){
      this.accountService.debit(accountId,amount,description).subscribe({
        next:(data)=>{
         alert("Success debit");
          this.handleSearchAccounts();
        },
        error : (err)=>{
          console.log(err);
      }
      });
    }else if(operationType=='CREDIT'){
      this.accountService.credit(accountId,amount,description).subscribe({
        next:(data)=>{
          alert("Success credit");
          this.handleSearchAccounts();
        },
        error : (err)=>{
          console.log(err);
        }
      });
    }else if(operationType=='TRANSFER'){
      this.accountService.transfer(accountId,accountDestination,amount,description).subscribe({
        next:(data)=>{
          alert("Success transfer");
          this.handleSearchAccounts();
        },
        error : (err)=>{
          console.log(err);
        }
      });
    }
    this.operationsFormGroup.reset();
  }
}
