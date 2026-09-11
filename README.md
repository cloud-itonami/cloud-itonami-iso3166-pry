# cloud-itonami-iso3166-pry

Open ISO 3166 Blueprint for **PRY**: Republic of Paraguay.

Independent public-sector market-entry & procurement-compliance service
for an already-incorporated operator entering public contracts in Paraguay.

## Official surface (web-verified)

- Procurement: DNCP Sistema de Informaciones de las Contrataciones Públicas (SICP)
- Business/tax: RUC via SET + commercial registration

## What this is NOT

- **Not the government of Paraguay.**

## Culture catalog

Alongside the statute catalog, this repo carries a **country-level
regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Paraguay:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.

## License

AGPL-3.0-or-later.
