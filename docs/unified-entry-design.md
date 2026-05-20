# TVBox Unified Entry Design

TVBox first release should not hardcode third-party source lists.

## Design
- App only keeps one configurable entry.
- Default entry should be replaced by a placeholder or your own managed URL.
- Users update source through one unified entry page.
- The unified page can later point to your own server, Pages, or other controlled hosting.

## Current state
- Existing built-in example URLs have been removed from visible UI hints.
- Next step is to wire the app to a single managed entry if needed.
