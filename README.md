 # PDF Compliance Validator  

## Key Checks  
- ✅ Audit trail completeness (§11.10)  
- ✅ Timestamp validation (ISO 8601)  
- ✅ Signature binding (§11.200)  

## Contribute  
1. Fork this repo  
2. Submit PRs to `src/`  
3. See [Contributing Guidelines](CONTRIBUTING.md) *(Optional)*

## Free Version Checks
```bash
java -jar validator.jar your_file.pdf
```
### What's Validated
- Basic metadata (Author, Title)  
- Timestamp presence (No format validation)  
- Signature existence (No chain validation)  

### Pro Version Offers
- ISO 8601 timestamp validation  
- RBAC configuration checks  
- SOP reference verification  
*Contact for enterprise features*
