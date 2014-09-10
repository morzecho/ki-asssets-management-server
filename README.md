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

## Pobieranie inforamcji o assetach
```
GET /android-api/assets/:id  
```

Odpowiedź:
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
