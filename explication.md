# Présentation du projet exo-bank-Java

**Ce projet est un projet de cours basé sur un exemple de cours, il a pour objectif de nous présenter les micro-services en JAVA.**

**Je dois créer 3 services : Account / Card / Loan**

**Ensuite, créer un service discovery / gateway / config-server**

## Création des services

Dans la création d'un service, on crée un projet **Maven** via **SpringBoot**

### Création du service *Account*

#### Création 
Pour la création du service **Account**, il faut utiliser les **Dépendances** suivantes :

- Spring Data JPA
- H2 Database
- Lombok
- Spring Web
- Spring Boot Dev Tools  

Pour appliquer directement les dépendances, on peut utiliser le site suivant :
https://start.spring.io/index.html

**ou**

Via **IJ**, on peut créer un projet avec les dépendances directement.

Une fois le projet crée, on envoie un premier commit de création du service : **Account**

**Account** :     
- private Long id;
- private String name;
- private String email;
- private Integer solde; 

#### Création du fichier *Entity*

On crée d'abord un package **Entity**, pour créer la class : *Account.java*

##### Entity : 

```Java
package org.example.accountservice.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer Solde;

    private int numberOfCards;

    private int numberOfLoans;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSolde() {
        return Solde;
    }

    public void setSolde(Integer solde) {
        Solde = solde;
    }

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	public int getNumberOfLoans() {
		return numberOfLoans;
	}

	public void setNumberOfLoans(int numberOfLoans) {
		this.numberOfLoans = numberOfLoans;
	}
}

```
Ensuite, on crée un package **Repository**, pour créer l'interface : *AccountRepository.java*

##### Repository :
```Java
package org.example.account.repository;

import org.example.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
}

```

Ensuite, on crée un package **Service**, pour créer l'interface : *AccountService.java*

##### Service :
```Java
package org.example.account.service;

import org.example.account.entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();

    public Account getAccountById(Long id);

    public Account saveAccount(Account account);

    public void deleteAccount(Long id);
}
```

Ensuite, on crée un package **ServiceImpl**, pour créer l'interface : *AccountServiceImpl.java*

##### ServiceImpl :
```Java
package org.example.account.serviceImpl;

import org.example.account.entity.Account;
import org.example.account.repository.AccountRepository;
import org.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
```


Ensuite, on crée un package **Controller**, pour créer l'interface : *AccountController.java*

##### Controller :
```Java
package org.example.account.controller;

import org.example.account.entity.Account;
import org.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        System.out.println(account.getName());
        return accountService.saveAccount(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
```

Après avoir créé tous ses fichiers, je peux maintenant tester si mon service fonctionne en utilisant les routes

Après avoir lancé **Postman**, on peut tester l'url suivante : 

```URL
http://localhost:8080/accounts
```

Avec ce Json par exemple :

```json
{
    "name": "thomas",
    "email": "thomas@ynov.com",
    "solde": 10
}
```
![account-post-1.png](img-md/account-post-1.png)
On peut voir ici que la création a bien été effectué, on peut vérifier également avec l'usage d'un getAll et/ou d'un getById.

![account-getall-1.png](img-md/account-getall-1.png)
![account-getbyid-1.png](img-md/account-getbyid-1.png)

On peut également tester la suppression : 

![account-delete-1.png](img-md/account-delete-1.png)
![account-getall-2.png](img-md/account-getall-2.png)

Une fois que tous fonctionne, on peut maintenant passer au deux autres services : **Card** et **Loan**

Avant cela, on peut maintenant faire un commit pour la creation final du service account

### Création du service *Card*

#### Création
Pour la création du service **Card**, il faut utiliser les **Dépendances** suivantes :

- Spring Data JPA
- H2 Database
- Lombok
- Spring Web
- Spring Boot Dev Tools  



