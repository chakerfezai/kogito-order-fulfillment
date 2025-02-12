📌 **User Stories Automatisation du provisioning des outils d’ingénierie plateforme

#### 🔹 **[US-X01] Conception de l’architecture et choix des outils

Concevoir l’architecture technique du provisioning et choisir les technologies les plus adaptées (Terraform,
Gitlab-ci...), **afin de** garantir une solution robuste, scalable et maintenable.

✅ **Critères d'acceptation** :

- Un document de cadrage détaillant les besoins et les contraintes est validé.
- Un schéma d’architecture est validé par l’équipe technique.
- Les outils à utiliser pour l'automatisation sont sélectionnés et documentés.
- Poc est validé.

⏳ **Estimation** : ~ 4/5 **j/h**

---

#### 🔹 **[US-X02] Développement du provisioning GitLab**

Automatiser la création et configuration des projets GitLab à partir du référentiel des produits, **afin de** éviter la
gestion manuelle des repositories et des permissions.

✅ **Critères d'acceptation** :

- Un nouveau produit ajouté au référentiel déclenche automatiquement la création d’un repository GitLab et groupes.

⏳ **Estimation** : ~ 2 **j/h**

---

#### 🔹 **[US-X03] Mapping des groupes Okta au groupes Gitlab via SAML

Créer les SAML link pour le mapping des groupes Okta au groupes Gitlab via SAML (SMAL Link)

✅ **Critères d'acceptation** :

- un nouveau repository Gitlab ajouté et un groupe qui corresponds, déclenche automatiquement la création d'un SMAL link
  avec Okta.

⌛**Estimation :** ~ 2 **j/h**

---

#### 🔹 **[US-X04] Développement du provisioning Artifactory

Provisionner automatiquement les dépôts Artifactory (Maven, Docker, NPM, etc.) selon les besoins définis dans le
référentiel des produits, **afin de** garantir une gestion standardisée des artefacts.

✅ **Critères d'acceptation** :

- Un produit du référentiel génère automatiquement plusieurs dépôts Artifactory.
- Les types de dépôts (Maven, Docker, Terraform, NPM etc.) sont configurés selon les spécifications du produit.

💡 **Notes complémentaires** :
⛔ Les noms des dépôts est fournit pas l'équipe Flames.

⌛ **Estimation** : ~ 2 **j/h**

---

#### 🔹 **[US-X05] Développement du provisioning Grafana**

Chaque produit du référentiel ait automatiquement une organisation (Grafana avec des dashboards et des alertes
préconfigurées ??) teams et tenant (topics kafka etc...) , **afin de** garantir une bonne observabilité dès le
déploiement.

✅ **Critères d'acceptation** :

- Lorsqu’un produit est ajouté au référentiel, une organisation et teams Grafana sont crées.
- le tenant est crée via un API
- Un dashboard standardisé est mis en place.

⌛ **Estimation** : ~ 4 **j/h**