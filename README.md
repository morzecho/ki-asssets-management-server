Assets Management Server
=========================

# Android API

Aplikacja wystawia RESTfulowe API, za pomocą którego można pobierać listę dostępnych assetów, zgłaszać awarie, oraz śledzić stan zgłoszenia. Do używania API wymagane jest posiadanie tokena, potwierdzającego tożsamość. 

## Pobieranie tokena
Aby pobrać token wymagane jest posiadanie maila w domenia `agh.edu.pl`. Aby wygenerować nowy token należy skorzystać z metody
```
POST /android-api/users/token/new
```
i w nagłówku "email" podać adres mailowy, na który ma zostać wysłany nowy token.

Wywołanie każdej następnej metody, będzie wymagało podanie headera `token'.

## Usunięcie tokena
```
DELETE /android-api/users/token                  
```

## Sprawdzenie czy token jest poprawny
```
GET /android-api/users/token/validness
```
Przykładowa odpowiedź:
```
{
    "tokenValid": true
}
```

## Pobieranie inforamcji o assetach
```
GET /android-api/assets/:id  
```

Przykładowa odpowiedź:
```
{
    "asset": {
        "id": 1,
        "name": "projector BENQ",
        "category": "projektory",
        "location": "3.27a"
    },
    "typicalBreakDowns": [
        "brak kabla d-sub",
        "brak pilota",
        "nie wyswietla obrazu"
    ]
}
```
## Pobranie informacji o zgłoszeniach użytkownika
```
GET /android-api/issues
```

Przykładowa odpowiedź 
```
[
    {
        "id": null,
        "asset": {
            "id": null,
            "name": "projektor benqu",
            "category": "projektor",
            "location": "3.27"
        },
        "breakDown": "Spalona lampa",
        "issueStatus": "NEW",
        "dateCreated": 1404690166000,
        "lastModified": 1404690166000,
        "response": "Without response"
    },
    {
        "id": null,
        "asset": {
            "id": null,
            "name": "projektor",
            "category": "projektory",
            "location": "2.45"
        },
        "breakDown": "nie dziala",
        "issueStatus": "NEW",
        "dateCreated": 1404681399000,
        "lastModified": 1404681399000,
        "response": "Without response"
    }
```

## Stworzenie awarii
```
POST /android-api/issues
```

Jako request body należy podać json, przykład:
```
{
    "id": 1,
    "asset": {
        "id": 1,
        "name": "projektor",
        "category": "projektory",
        "location": "2.45"
    },
    "breakDown": "nie dziala",
    "issueStatus": "NEW",
    "dateCreated": 1403390020767,
    "lastModified": 1403390020767,
    "response": "empty"
}
```
oraz w headerze ustawić "Content-Type" = "application/json"
